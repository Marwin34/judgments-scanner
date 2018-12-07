package agh.cs.project.Commands;

import agh.cs.project.JSONParser;

public class DisplayRubrums implements ICommand {

    private final JSONParser parser;

    public DisplayRubrums(JSONParser parser) {
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
            bob.append(parser.getJudgments().get(signature).showRubrum());

        return bob.toString();
    }
}
