package agh.cs.project.Commands;

import agh.cs.project.JSONClasses.JSONParser;

public class DisplayJustifications implements ICommand {

    private final JSONParser parser;

    public DisplayJustifications(JSONParser parser) {
        this.parser = parser;
    }

    @Override
    public String execute() {
        return null;
    }

    @Override
    public String execute(String[] args) {
        StringBuilder bob = new StringBuilder();
        for(String signature : args)
            bob.append(parser.getJudgments().get(signature).showJustification());

        return bob.toString();
    }

    @Override
    public String description() {
        return "Wyswietla uzasadnienie danego orzeczenia.";
    }
}
