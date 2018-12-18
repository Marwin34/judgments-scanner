package agh.cs.project.Commands;

import agh.cs.project.Model.DataLoader;

import java.util.List;

public class DisplayJustifications implements ICommand {

    private final DataLoader loader;

    public DisplayJustifications(DataLoader loader) {
        this.loader = loader;
    }

    @Override
    public String execute() {
        return null;
    }

    @Override
    public String execute(List<String> args) {
        StringBuilder bob = new StringBuilder();
        for(String signature : args)
            bob.append(loader.getJudgments().get(signature).showJustification());

        return bob.toString();
    }

    @Override
    public String description() {
        return "Wyswietla uzasadnienie danego orzeczenia.";
    }
}
