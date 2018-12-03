package agh.cs.project;

import java.util.Map;

public class TimeStatistics extends Statistics implements ICommand {
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
        return judgmentsStatisticsOverYears.get(year).toString();
    }

    public String yearStats() {
        StringBuilder bob = new StringBuilder("Liczba orzeczen w kazdym miesiacy kazdego roku:\n");
        for (Map.Entry<String, MonthsElement> element : judgmentsStatisticsOverYears.entrySet()) {
            String toAppend = "ROK " + element.getKey() + " prezezntowal sie nastepujaco:\n" + element.getValue();
            bob.append(toAppend);
        }

        return bob.toString();
    }
}
