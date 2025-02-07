package by.stepanenko.notebook.service;

import java.time.LocalDate;
import java.util.List;

import by.stepanenko.notebook.dao.DAOException;
import by.stepanenko.notebook.entity.Note;

public interface NotebookService {

	void createNewFile() throws ServiceException;

	void add(Note n) throws ServiceException;

	List<Note> find(String text) throws ServiceException;

	List<Note> find(LocalDate date) throws ServiceException;

	List<Note> allNotes() throws ServiceException;

	void save() throws ServiceException;

	void updateNoteByID(String id, String newValue) throws ServiceException;

}
