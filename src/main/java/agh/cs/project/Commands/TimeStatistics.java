package agh.cs.project.Commands;

import agh.cs.project.Model.Statistics;

import java.util.Map;

public class TimeStatistics implements ICommand {

    private final Statistics statistics;

    public TimeStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public String execute() {
        return yearStats();
    }

    public String execute(String[] args) {
        StringBuilder bob = new StringBuilder("Statystyki na miesiace ");

        for(String month : args){
            bob.append(monthStats(month));
            bob.append("\n");
        }

        return bob.toString();
    }

    public String monthStats(String year) {
        return statistics.getJudgmentsStatisticsOverYears().get(year).toString();
    }

    public String yearStats() {
        StringBuilder bob = new StringBuilder("Liczba orzeczen w kazdym miesiacy kazdego roku:\n");
        for (Map.Entry<String, Statistics.MonthsElement> element : statistics.getJudgmentsStatisticsOverYears().entrySet()) {
            String toAppend = "ROK " + element.getKey() + " prezezntowal sie nastepujaco:\n" + element.getValue();
            bob.append(toAppend);
        }

        return bob.toString();
    }

    @Override
    public String description() {
        return "Wyswetla statystki ze wzgledu na rok lub miesiac.";
    }
}
