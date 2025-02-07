package by.stepanenko.notebook.controller.impl;

import by.stepanenko.notebook.controller.Command;
import by.stepanenko.notebook.controller.ControllerException;
import by.stepanenko.notebook.entity.ControllerParams;
import by.stepanenko.notebook.entity.Note;
import by.stepanenko.notebook.service.ServiceException;
import by.stepanenko.notebook.service.ServiceProvider;
import by.stepanenko.notebook.service.NotebookService;


public class AddNoteCommand implements Command {
	
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private final NotebookService service = serviceProvider.getNotebookService();

	@Override
	public String execute(ControllerParams params) throws ControllerException {

		Note newNote;

		newNote = new Note(params.getContent());
        try {
            service.add(newNote);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }

        return "Note added";
	}

}
