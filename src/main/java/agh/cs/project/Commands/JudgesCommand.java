package agh.cs.project.Commands;

import agh.cs.project.Model.Statistics;

import java.util.List;

import static org.fusesource.jansi.Ansi.ansi;

public class JudgesCommand implements ICommand {

    private final Statistics statistics;

    public JudgesCommand(Statistics statistics) {
        this.statistics = statistics;
    }

    public String execute() {
        return getTop10Judges();
    }

    public String execute(List<String> args) {
        return "Too many arguments! For more information use help."+ System.lineSeparator();
    }

    public String getTop10Judges() {
        StringBuilder bob = new StringBuilder();

        statistics.getJudgesByJudgments()
                .stream()
                .limit(10)
                .map(judge -> ansi().format("%-25s %s%n",judge.getName(), judge.getNumberOfCases()))
                .forEach(bob::append);

        return bob.toString();
    }

    @Override
    public String description() {
        return "Display top 10 judges ordered by number of judgments thy took part in. judges.";
    }
}
