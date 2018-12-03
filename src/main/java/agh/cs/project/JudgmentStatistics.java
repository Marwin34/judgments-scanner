package agh.cs.project;

import java.util.Collections;

public class JudgmentStatistics extends Statistics implements ICommand {
    public String execute() {
        return getJudgesToJudgment();
    }

    public String execute(String[] args) {
        return execute();
    }

    public String getJudgesToJudgment() {
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
