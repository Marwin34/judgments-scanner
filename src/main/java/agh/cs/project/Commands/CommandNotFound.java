package agh.cs.project.Commands;

import java.util.List;

public class CommandNotFound implements ICommand {
    @Override
    public String execute() {
        return "Nieznana komenda. \n";
    }

    @Override
    public String execute(List<String> args) {
        return execute();
    }

    @Override
    public String description() {
        return "";
    }
}
