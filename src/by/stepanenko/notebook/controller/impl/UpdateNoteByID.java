package by.stepanenko.notebook.controller.impl;

import by.stepanenko.notebook.controller.Command;
import by.stepanenko.notebook.controller.ControllerException;
import by.stepanenko.notebook.entity.ControllerParams;
import by.stepanenko.notebook.service.NotebookService;
import by.stepanenko.notebook.service.ServiceException;
import by.stepanenko.notebook.service.ServiceProvider;
import by.stepanenko.notebook.utils.SymbolsValidator;

public class UpdateNoteByID implements Command {

    private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
    private final NotebookService service = serviceProvider.getNotebookService();

    @Override
    public String execute(ControllerParams params) throws ControllerException {

        SymbolsValidator symbolsValidator = new SymbolsValidator();
        try {
            if (symbolsValidator.match(params.getContent())){
                throw new ControllerException("Cannot update note: unsupported symbol");
            }
            service.updateNoteByID(params.getId(), params.getContent());
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return "Note updated";
    }
}