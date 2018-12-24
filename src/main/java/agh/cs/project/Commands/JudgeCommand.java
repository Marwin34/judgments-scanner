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
            judge = judge.replaceAll("\"", " ").trim();
            if (loader.getJudges().containsKey(judge)) {
                bob
                        .append(judge)
                        .append(": ")
                        .append(loader.getJudges()
                                .get(judge).getNumberOfCases())
                        .append(" judgement(s).")
                        .append(System.lineSeparator());
            } else {
                bob
                        .append(judge)
                        .append(" not exists.")
                        .append(System.lineSeparator());
            }
        }

        return bob.toString();
    }

    @Override
    public String description() {
        return "Display number of judgments for specified judge.";
    }
}
