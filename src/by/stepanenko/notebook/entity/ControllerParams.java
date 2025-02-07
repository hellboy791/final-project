package by.stepanenko.notebook.entity;

import by.stepanenko.notebook.controller.ControllerException;
import by.stepanenko.notebook.utils.DateFormatValidator;
import by.stepanenko.notebook.utils.SymbolsValidator;

import java.io.Serializable;
import java.util.Objects;

public class ControllerParams implements Serializable {

    private String commandName;
    private String date;
    private String content;
    private String id;

    public ControllerParams(){}

    public ControllerParams(String commandName, String date, String content, String id) {
        this.commandName = commandName;
        this.date = date;
        this.content = content;
        this.id = id;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void validateParams() throws ControllerException{
        DateFormatValidator dateFormatValidator = new DateFormatValidator();
        SymbolsValidator symbolsValidator = new SymbolsValidator();

        if (this.date != null){
            if (!dateFormatValidator.match(this.getDate())){
                throw new ControllerException("Unsupported date format");
            }
        }

        if (this.content != null){
            if (symbolsValidator.match(this.getContent())){
                throw new ControllerException("Unsupported symbol");
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ControllerParams that = (ControllerParams) o;
        return id.equals(that.id) && Objects.equals(commandName, that.commandName) && Objects.equals(date, that.date) && Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commandName, date, content, id);
    }

    @Override
    public String toString() {
        return "ControllerParams{" +
                "command=" + commandName +
                ", date=" + date +
                ", content='" + content + '\'' +
                ", id=" + id +
                '}';
    }



}
