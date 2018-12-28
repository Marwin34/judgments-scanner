package agh.cs.project.Commands;

import agh.cs.project.Model.Statistics;

import java.util.List;

public class JuryCommand implements ICommand {
    private final Statistics statistics;

    public JuryCommand(Statistics statistics) {
        this.statistics = statistics;
    }

    @Override
    public String execute() {
        return "Too many arguments! For more information use help." + System.lineSeparator();
    }

    @Override
    public String execute(List<String> args) {
        StringBuilder bob = new StringBuilder();

        for (String judgesNumber : args) {
            if (statistics.getJuryStatistic().containsKey(Integer.parseInt(judgesNumber))) {
                bob.append(String.format("%-10s %s %s%n", judgesNumber, statistics.getJuryStatistic().get(Integer.parseInt(judgesNumber)), "judgment(s)."));
            } else {
                bob.append(String.format("%-10s %s%n", judgesNumber, "0 judgment(s)."));
            }
        }
        return bob.toString();
    }

    @Override
    public String description() {
        return "Display number of cases which have specified number of judges. jury <judges_number> ...";
    }
}
