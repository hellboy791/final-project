package by.stepanenko.notebook.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import by.stepanenko.notebook.dao.DAOException;
import by.stepanenko.notebook.dao.NoteBookProvider;
import by.stepanenko.notebook.dao.NoteBookDao;
import by.stepanenko.notebook.entity.Note;
import by.stepanenko.notebook.service.NotebookService;
import by.stepanenko.notebook.service.ServiceException;

public class NotebookServiceImpl implements NotebookService {
	private final NoteBookProvider noteBookProvider = NoteBookProvider.getInstance();
	private final NoteBookDao dao = noteBookProvider.getNoteBookDao();

	@Override
	public void createNewFile() throws ServiceException{
        try {
            dao.createNewFile();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
	
	public void add(Note n) throws ServiceException {
        try {
            dao.addNote(n);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
	
	@Override
	public List<Note> find(String text) throws ServiceException {

		List<Note> result = new ArrayList<>();
        List<Note> myNotes = null;
        try {
            myNotes = dao.allNotes();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        for(Note n : myNotes) {
			if(isTextInNote(n, text)) {
				result.add(n);
			}
		}
		return result;
	}
	
	private boolean isTextInNote(Note n, String text) {
		return n.getContent().contains(text);
	}

	@Override
	public List<Note> find(LocalDate date) throws ServiceException {
		List<Note> result = new ArrayList<>();

        List<Note> myNotes = null;
        try {
            myNotes = dao.allNotes();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        for (Note note: myNotes){
			if (date.equals(note.getDate())){
				result.add(note);
			}
		}
		return result;
	}

	@Override
	public List<Note> allNotes() throws ServiceException {
        try {
            return dao.allNotes();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

	@Override
	public void save() throws ServiceException {
        try {
            dao.save();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

	@Override
	public void updateNoteByID(String id, String newValue) throws ServiceException {
        try {
            dao.updateNoteByID(id, newValue);
        } catch (RuntimeException | DAOException e) {
            throw new ServiceException(e);
        }
    }

}
