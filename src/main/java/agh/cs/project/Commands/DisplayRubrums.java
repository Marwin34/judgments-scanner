package agh.cs.project.Commands;

import agh.cs.project.DataLoader;

public class DisplayRubrums implements ICommand {

    private final DataLoader loader;

    public DisplayRubrums(DataLoader loader) {
        this.loader = loader;
    }

    @Override
    public String execute() {
        return null;
    }

    @Override
    public String execute(String[] args) {
        StringBuilder bob = new StringBuilder();
        for(String signature : args)
            bob.append(loader.getJudgments().get(signature).showRubrum());

        return bob.toString();
    }

    @Override
    public String description() {
        return "Wyswietla metryke danej sprawy.";
    }
}
