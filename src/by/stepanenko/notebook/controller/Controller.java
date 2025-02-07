package by.stepanenko.notebook.controller;

import by.stepanenko.notebook.entity.ControllerParams;

public class Controller {

	private final CommandProvider provider = new CommandProvider();
	
	public String doAction(ControllerParams params) {

		Command executionCommand;
		String response;
		String[] res;

		try {
			params.validateParams();
			executionCommand = provider.getCommand(params.getCommandName().toUpperCase());
			response = executionCommand.execute(params);

		} catch (ControllerException e) {
			res = e.getMessage().split(":");
			response = res[res.length - 1].trim();

        }

		return response;
	}

}