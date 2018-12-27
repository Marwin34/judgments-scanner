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
        bob.append("Dostepne komendy")
                .append(System.lineSeparator());

        for (Map.Entry<String, ICommand> entry : commands.entrySet()) {
            bob.append(String.format("%-15s %s%n", entry.getKey(), entry.getValue().description()));
        }

        bob.append("Program obsługuje argumenty odzielone spacjami, jeśli argument zawiera spacje należy zamknąć go znakami \".")
                .append(System.lineSeparator());

        return bob.toString();
    }

    @Override
    public String execute(List<String> args) {
        return execute();
    }

    @Override
    public String description() {
        return "Zwraca liste dostepnych komend.";
    }
}
