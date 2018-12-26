package agh.cs.project.Commands;

import agh.cs.project.Model.Statistics;

import java.util.List;

import static org.fusesource.jansi.Ansi.ansi;

public class CourtsCommand implements ICommand {

    private final Statistics statistics;


    public CourtsCommand(Statistics statistics) {
        this.statistics = statistics;
    }

    public String execute() {
        return getCourtsStatistics();
    }

    public String execute(List<String> args) {
        return execute();
    }

    public String getCourtsStatistics() {
        StringBuilder bob = new StringBuilder();

        statistics.getCourtsStats().sort((o1, o2) -> o2.getNumberOfJudgments() - o1.getNumberOfJudgments());

        statistics.getCourtsStats()
                .stream()
                .map(court -> ansi().format("%-30s %s%n", court.toString(), court.getNumberOfJudgments()))
                .forEach(bob::append);

        return bob.toString();
    }

    @Override
    public String description() {
        return "Zwraca statystyke sadow ze wzgledu na liczbe wydanych orzeczen.";
    }
}
