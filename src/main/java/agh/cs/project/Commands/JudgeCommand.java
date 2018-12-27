package agh.cs.project.Commands;

import agh.cs.project.Model.DataLoader;

import java.util.List;

public class JudgeCommand implements ICommand {

    private final DataLoader loader;

    public JudgeCommand(DataLoader loader) {
        this.loader = loader;
    }

    @Override
    public String execute() {
        return "You need to specify judge." + System.lineSeparator();
    }

    @Override
    public String execute(List<String> args) {
        StringBuilder bob = new StringBuilder();

        for (String judge : args) {
            if (loader.getJudges().containsKey(judge)) {
                bob.append(String.format("%-25s %s %s%n", judge, loader.getJudges().get(judge).getNumberOfCases(), "judgment(s)."));
            } else {
                bob.append(String.format("%-25s %s%n", judge, "nie istnieje."));
            }
        }

        return bob.toString();
    }

    @Override
    public String description() {
        return "Display number of judgments for specified judge.";
    }
}
