package agh.cs.project.Commands;

import agh.cs.project.Model.DataLoader;

import java.util.List;

public class ContentCommand implements ICommand {

    private final DataLoader loader;

    public ContentCommand(DataLoader loader) {
        this.loader = loader;
    }

    @Override
    public String execute() {
        return String.format("%s%n", "Missing arguments! For more information use help.");
    }

    @Override
    public String execute(List<String> args) {
        StringBuilder bob = new StringBuilder();
        for (String signature : args) {
            if (loader.getJudgments().containsKey(signature)) {
                bob.append(String.format("%-15s %s%n", "Uzasadnienie orzeczenia ", signature));
                bob.append(String.format("%s%n", loader.getJudgments().get(signature).showJustification()));
            } else {
                bob.append("There is no judgment specified by ")
                        .append(signature)
                        .append(System.lineSeparator());
            }

        }
        return bob.toString();
    }

    @Override
    public String description() {
        return "Display justification of specified judgemnt(s). content <signature> ...";
    }
}
