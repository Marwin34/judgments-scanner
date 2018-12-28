package agh.cs.project.Commands;

import java.util.List;
import java.util.Map;

public class HelpCommand implements ICommand {

    private final Map<String, ICommand> commands;

    public HelpCommand(Map<String, ICommand> commands) {
        this.commands = commands;
    }

    @Override
    public String execute() {

        StringBuilder bob = new StringBuilder();
        bob.append("Available commands:")
                .append(System.lineSeparator());

        for (Map.Entry<String, ICommand> entry : commands.entrySet()) {
            bob.append(String.format("%-15s %s%n", entry.getKey(), entry.getValue().description()));
        }

        bob.append("To specify arguments u need to separate command and arguments with space. If u need to use argument containing white symbols u have to cover it with \".")
                .append(System.lineSeparator());

        return bob.toString();
    }

    @Override
    public String execute(List<String> args) {
        return "Too many arguments! For more help use help." + System.lineSeparator();
    }

    @Override
    public String description() {
        return "Display list of available commands. Used without arguments";
    }
}
