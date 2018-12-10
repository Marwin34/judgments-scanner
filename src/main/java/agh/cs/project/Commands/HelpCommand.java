package agh.cs.project.Commands;

import java.util.Map;

public class HelpCommand implements ICommand {

    private final Map<String, ICommand> commands;

    public HelpCommand(Map<String, ICommand> commands){
        this.commands = commands;
    }

    @Override
    public String execute() {

        StringBuilder bob = new StringBuilder("Dostepne komendy: \n");

        for(Map.Entry<String, ICommand> entry : commands.entrySet()){
            bob.append(entry.getKey()).append(": ").append(entry.getValue().description()).append("\n");
        }
        bob.append("help").append(": ").append(description()).append("\n");

        return bob.toString();
    }

    @Override
    public String execute(String[] args) {
        return execute();
    }

    @Override
    public String description() {
        return "Zwraca liste dostepnych komend.";
    }
}
