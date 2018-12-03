package agh.cs.project;

import agh.cs.project.JSONClasses.JSONJudgment;
import agh.cs.project.JSONClasses.JSONRegulation;

import java.util.*;

public class Statistics {

    protected class MonthsElement {
        private int[] judgmentsPerMonth;

        public MonthsElement() {
            judgmentsPerMonth = new int[12];

            for (int i = 0; i < 12; i++)
                judgmentsPerMonth[i] = 0;
        }

        @Override
        public String toString() {
            String months[] = {"Styczen", "Luty", "Marzec", "Kwiecien", "Maj", "Czerwiec", "Lipiec", "Sierpien", "Wrzesien",
                    "Pazdziernik", "Listopad", "Grudizen" };

            StringBuilder bob = new StringBuilder("Statystyki wg. miesiecy:\n");
            for (int i = 0; i < 12; i++) {
                bob.append(months[i]).append(" : ").append(judgmentsPerMonth[i]).append("\n");
            }

            return bob.toString();
        }

        public void increment(int month) {
            judgmentsPerMonth[month]++;
        }
    }

    protected ArrayList<Court> courtsStats;
    protected Map<Integer, ReferencedRegulation> regulations;
    protected HashMap<String, Integer> judgmentJudgesStats;
    protected HashMap<String, MonthsElement> judgmentsStatisticsOverYears;
    protected List<Judge> judgesByJudgments;

    public Statistics() {
        courtsStats = new ArrayList<Court>();
        regulations = new HashMap<Integer, ReferencedRegulation>();
        judgmentJudgesStats = new HashMap<String, Integer>();
        judgmentsStatisticsOverYears = new HashMap<String, MonthsElement>();
    }

    public void load(List<JSONJudgment> judgments, Map<String, Judge> judges) {
        convertJudgesToTop10(judges);
        for (JSONJudgment judgment : judgments) {
            createYearlyStatistics(judgment);
            judgesNumberToJudgment(judgment);
            createCourt(judgment);
            createRegulationStats(judgment.getReferencedRegulations());
        }
    }

    private void convertJudgesToTop10(Map<String, Judge> judges){
        judgesByJudgments = new ArrayList<Judge>(judges.values());
        Collections.sort(judgesByJudgments, new Comparator<Judge>() {
            public int compare(Judge o1, Judge o2) {
                return o2.getNumberOfCases() - o1.getNumberOfCases(); // Descending.
            }
        });
        judgesByJudgments = judgesByJudgments.subList(0, 10);
    }

    private void judgesNumberToJudgment(JSONJudgment judgment) {
        judgmentJudgesStats.put(judgment.getSignature(), judgment.numberOfJudges());
    }

    private void createYearlyStatistics(JSONJudgment judgment) {
        String year = judgment.getJudgmentDate().split("-")[0];
        String id = judgment.getJudgmentDate().split("-")[1];

        int i = Integer.parseInt(id) - 1;
        if (i >= 0 && i < 12) {
            if (judgmentsStatisticsOverYears.get(year) == null) {
                MonthsElement element = new MonthsElement();
                element.increment(i);
                judgmentsStatisticsOverYears.put(year, element);
            } else {
                judgmentsStatisticsOverYears.get(year).increment(i);
            }
        }
    }

    private void createCourt(JSONJudgment judgment){
        Court newCourt = new Court(judgment.getCourtType());

        for(Court court : courtsStats){
            if(court.equals(newCourt)) {
                court.incrementNumberOfJudgments();
                return;
            }
        }
        courtsStats.add(newCourt);
    }

    private void createRegulationStats(List<JSONRegulation> JSONregulations){
        for(JSONRegulation JSONRegulation : JSONregulations){
            ReferencedRegulation newRegulation = new ReferencedRegulation(JSONRegulation);

            if(regulations.containsValue(newRegulation)){
                regulations.get(newRegulation.hashCode()).incremenetNumberOfReferentions();
            }else{
                regulations.put(newRegulation.hashCode(), newRegulation);
            }
        }
    }
}
