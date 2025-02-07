package by.stepanenko.notebook.dao;

import by.stepanenko.notebook.entity.Note;
import java.util.List;

public interface NoteBookDao {

    List<Note> allNotes() throws DAOException;

    void addNote(Note note) throws DAOException;

    void save() throws DAOException;

    void updateNoteByID(String id, String newValue) throws DAOException;

    void createNewFile() throws DAOException;

}
