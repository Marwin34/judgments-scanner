package agh.cs.project;

import agh.cs.project.Commands.ICommand;

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

    public String getJudgesToJudgment() {

        HashMap<String, Integer> judgmentJudgesStats = statistics.getJudgmentJudgesStats();
        
        int min = 0, avg = 0, max = 0;

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
