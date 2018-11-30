package agh.cs.project;

import java.util.*;

public class Statistics {

    private class MonthsElement {
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

    private ArrayList<Court> courtsStats;
    private Map<Integer, ReferencedRegulation> regulations;
    private HashMap<String, Integer> judgmentJudgesStats;
    private HashMap<String, MonthsElement> judgmentsStatisticsOverYears;
    private List<Judge> judgesByJudgments;

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

    public String getJudgesToJudgment() {
        int min = 0, avg = 0, max = 0;

        min = Collections.min(judgmentJudgesStats.values());

        for (Integer value : judgmentJudgesStats.values()) {
            avg += value;
        }
        avg /= judgmentJudgesStats.size();

        max = Collections.max(judgmentJudgesStats.values());

        return "Najmniejsza liczba sedziow w orzeczeniu to: " + min + "\nnatomiast najwieksza liczba swedziow " +
                "przypadajacych na orzeczenie to: " + max + "\n Srednia liczba sedziow na orzeczenie: " + avg + ".";
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

    public String getTop10Regulations() {
        StringBuilder bob = new StringBuilder();

        List<ReferencedRegulation> regulationsByReferentions = new ArrayList<ReferencedRegulation>(regulations.values());
        Collections.sort(regulationsByReferentions, new Comparator<ReferencedRegulation>() {
            public int compare(ReferencedRegulation o1, ReferencedRegulation o2) {
                return o2.getNumberOfReferentions() - o1.getNumberOfReferentions(); // Descending.
            }
        });

        for (int i = 0; i < 10; i++) {
            ReferencedRegulation referencedRegulation = regulationsByReferentions.get(i);
            String regulationStat = i + 1 + ": " + referencedRegulation + "\n";
            bob.append(regulationStat);
        }
        return bob.toString();
    }

    public String getTop10Judges() {
        StringBuilder bob = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            Judge judge = judgesByJudgments.get(i);
            String judgeStats = i + 1 + ": " + judge.getName() + ": " + judge.getNumberOfCases() + "\n";
            bob.append(judgeStats);
        }
        return bob.toString();
    }
}
