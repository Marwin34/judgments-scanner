package agh.cs.project;

import java.util.Collections;
import java.util.Comparator;

public class CourtStatistics extends Statistics implements ICommand {

    public String execute() {
        return getCourtsStatistics();
    }

    public String execute(String[] args) {
        return execute();
    }

    public String getCourtsStatistics() {
        StringBuilder bob = new StringBuilder();

        Collections.sort(courtsStats, new Comparator<Court>() {
            public int compare(Court o1, Court o2) {
                return o2.getNumberOfJudgments() - o1.getNumberOfJudgments(); // Descending.
            }
        });

        for (Court court : courtsStats) {
            bob.append(court);
            bob.append("\n");
        }

        return bob.toString();
    }
}
