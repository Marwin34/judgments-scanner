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
        return "Too many arguments! For more information use help.";
    }

    @Override
    public String description() {
        return "Used to exit from program. exit.";
    }
}
