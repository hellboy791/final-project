package by.stepanenko.notebook.controller.impl;

import by.stepanenko.notebook.controller.Command;
import by.stepanenko.notebook.controller.ControllerException;
import by.stepanenko.notebook.entity.ControllerParams;
import by.stepanenko.notebook.entity.Note;
import by.stepanenko.notebook.service.ServiceException;
import by.stepanenko.notebook.service.ServiceProvider;
import by.stepanenko.notebook.service.NotebookService;
import by.stepanenko.notebook.utils.Delimeters;

import java.util.List;

public class GetAllNotes implements Command {
    private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
    private final NotebookService service = serviceProvider.getNotebookService();

    @Override
    public String execute(ControllerParams params) throws ControllerException {
        StringBuilder response = new StringBuilder();
        List<Note> notes = null;
        try {
            notes = service.allNotes();
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }

        if (notes.isEmpty()){
            throw new ControllerException("-NoteBook is empty-");
        }

        for (Note n: notes){
            response.append(n.toString()).append(Delimeters.NOTES_DELIMITER);
        }

        return response.toString();
    }
}
