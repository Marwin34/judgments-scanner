package agh.cs.project.Commands;

import java.util.List;

public class ExitCommand implements ICommand {

    @Override
    public String execute() {
        System.exit(0);
        return null;
    }

    @Override
    public String execute(List<String> args) {
        return execute();
    }

    @Override
    public String description() {
        return "Used to exit from program.";
    }
}
