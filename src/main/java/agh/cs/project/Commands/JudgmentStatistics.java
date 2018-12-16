package agh.cs.project.Commands;

import agh.cs.project.Model.Statistics;

import java.util.Collections;
import java.util.HashMap;

public class JudgmentStatistics implements ICommand {

    private final Statistics statistics;

    public JudgmentStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public String execute() {
        return getJudgesToJudgment();
    }

    public String execute(String[] args) {
        return execute();
    }

    @Override
    public String description() {
        return "Statystyki sedziow do orzeczenia.";
    }

    public String getJudgesToJudgment() {

        HashMap<String, Integer> judgmentJudgesStats = statistics.getJudgmentJudgesStats();
        
        int min, avg = 0, max;

        min = Collections.min(judgmentJudgesStats.values());

        for (Integer value : judgmentJudgesStats.values()) {
            avg += value;
        }
        avg /= judgmentJudgesStats.size();

        max = Collections.max(judgmentJudgesStats.values());

        return "Najmniejsza liczba sedziow w orzeczeniu to: " + min + "\nnatomiast najwieksza liczba swedziow " +
                "przypadajacych na orzeczenie to: " + max + "\n Srednia liczba sedziow na orzeczenie: " + avg + ".\n";
    }
}
