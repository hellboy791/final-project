package by.stepanenko.notebook.dao.impl;

import by.stepanenko.notebook.dao.DAOException;
import by.stepanenko.notebook.dao.NoteBookDao;
import by.stepanenko.notebook.entity.Note;
import by.stepanenko.notebook.utils.Delimeters;
import by.stepanenko.notebook.utils.IDGenerator;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileNoteBookDao implements NoteBookDao {

    private static List<Note> notes;
    private final String filePath = System.getProperty("user.home") + "/NoteBook.txt";
    private final String columnSeparator = ";"; //разделитель между колонками

    private static FileNoteBookDao instance;

    public FileNoteBookDao() throws DAOException {
        readNote();
    }

    public static FileNoteBookDao getInstance() throws DAOException {
        if (instance == null){
            try {
                instance = new FileNoteBookDao();
            } catch (RuntimeException e) {
                throw new DAOException("Error get FileNoteBookDao INSTANCE" , e);
            }
        }
        return instance;
    }

    @Override
    public void createNewFile() throws DAOException{
        File file = new File(filePath);
        try {
            if (!file.createNewFile()){
                throw new DAOException("Cannot create file");
            }
        } catch (IOException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<Note> allNotes() {
        return notes;
    }

    @Override
    public void addNote(Note note) {
        notes.add(note);
    }

    @Override
    public void save() throws DAOException {
        FileWriter writer = null;
        File file = new File(filePath);

        //если файла нет, то создаем чтоб не потерять при выходе все записи, что добавили
        if (!file.exists()){
            createNewFile();
        }

        try {
            writer = new FileWriter(filePath, false);
            for (Note note : notes) {
                writer.write(note.getDate().toString() + columnSeparator + note.getContent() + Delimeters.NOTES_DELIMITER);
            }
        } catch (FileNotFoundException e) {
            throw new DAOException("File not found", e);
        } catch (IOException e) {
            throw new DAOException("Something went wrong", e);
        } finally {
            if (writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new DAOException("File close error",e);
                }
            }
        }
    }

    public void readNote() throws DAOException{
        String[] ts;
        File file = new File(filePath);

        //если notes пустое, то инициализируем пустой list и пытаемся прочитать файл по строкам
        if (notes == null){
            notes = new ArrayList<>();
            if (file.exists()){
                try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                    String strCurrentLine;
                    while ((strCurrentLine = reader.readLine()) != null){
                        ts = strCurrentLine.split(columnSeparator);
                        notes.add(new Note(String.valueOf(IDGenerator.getNextId()), LocalDate.parse(ts[0]), ts[1]));
                    }
                } catch (FileNotFoundException e) {
                    throw new DAOException("File not found");
                } catch (IOException e) {
                    throw new DAOException("Something went wrong");
                }
            }
        }
    }

    public Note getNoteByID(String id){
        if (notes != null){
            for (Note n: notes){
                if (n.getId().equals(id)){
                    return n;
                }
            }
        }
        return null;
    }

    @Override
    public void updateNoteByID(String id, String newValue) throws DAOException {
        Note note = getNoteByID(id);
        if (note != null){
            note.setContent(newValue);
        } else {
            throw new DAOException("Cannot find note with id " + id);
        }
    }

}
