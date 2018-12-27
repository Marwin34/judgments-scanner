package agh.cs.project.Commands;

import agh.cs.project.Model.DataLoader;

import java.util.List;

import static org.fusesource.jansi.Ansi.ansi;

public class RubrumCommand implements ICommand {

    private final DataLoader loader;

    public RubrumCommand(DataLoader loader) {
        this.loader = loader;
    }

    @Override
    public String execute() {
        return String.format("%s%n", "Nie wprowadzono argumentow. Po wiecej informacji uzyj komendy help!");
    }

    @Override
    public String execute(List<String> args) {
        StringBuilder bob = new StringBuilder();
        for (String signature : args) {
            if (loader.getJudgments().containsKey(signature)) {
                bob.append(loader.getJudgments().get(signature).showRubrum());
            } else {
                bob.append("Can't find signature ")
                        .append(signature)
                        .append(System.lineSeparator());
            }
        }
        return bob.toString();
    }

    @Override
    public String description() {
        return "Wyswietla metryke danej sprawy.";
    }
}
