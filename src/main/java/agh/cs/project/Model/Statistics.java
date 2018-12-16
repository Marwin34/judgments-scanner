package agh.cs.project.Model;

import agh.cs.project.HTMLCLasses.HTMLJudgment;
import agh.cs.project.HTMLCLasses.HTMLRegulation;
import agh.cs.project.JSONClasses.JSONJudgment;
import agh.cs.project.JSONClasses.JSONRegulation;
import org.jsoup.nodes.Document;

import java.util.*;
import java.util.regex.Matcher;

public class Statistics {

    public class MonthsElement {
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
        courtsStats = new ArrayList<>();
        regulations = new HashMap<>();
        judgmentJudgesStats = new HashMap<>();
        judgmentsStatisticsOverYears = new HashMap<>();
        judgesByJudgments = new ArrayList<>();
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

    public void loadHTML(List<HTMLJudgment> judgments, Map<String, Judge> judges){
        convertJudgesToTop10(judges);
        for (HTMLJudgment judgment : judgments) {
            createYearlyStatistics(judgment);
            judgesNumberToJudgment(judgment);
            createCourt(judgment);
            createRegulationStats2(judgment.getReferencedRegulations());
        }
    }

    private void convertJudgesToTop10(Map<String, Judge> judges){
        if(judgesByJudgments.isEmpty()){
            judgesByJudgments.addAll(judges.values());

        }else{
            for(Map.Entry<String, Judge> judge : judges.entrySet()){
                if(!judgesByJudgments.contains(judge.getValue()))
                    judgesByJudgments.add(judge.getValue());
            }
        }

        judgesByJudgments.sort((o1, o2) -> {
            return o2.getNumberOfCases() - o1.getNumberOfCases(); // Descending.
        });
    }

    private void judgesNumberToJudgment(JSONJudgment judgment) {
        judgmentJudgesStats.put(judgment.getSignature(), judgment.numberOfJudges());
    }

    private void judgesNumberToJudgment(HTMLJudgment judgment) {
        judgmentJudgesStats.put(judgment.getSignature(), judgment.numberOfJudges());
    }

    private void createYearlyStatistics(JSONJudgment judgment) {
        String year = judgment.getJudgmentDate().split("-")[0];
        String id = judgment.getJudgmentDate().split("-")[1];

        int i = Integer.parseInt(id) - 1;
        addToMonth(year, i);
    }

    private void createYearlyStatistics(HTMLJudgment judgment) {
        String year = judgment.getJudgmentDate().split("-")[0];
        String id = judgment.getJudgmentDate().split("-")[1];

        int i = Integer.parseInt(id) - 1;
        addToMonth(year, i);
    }

    private void addToMonth(String year, int i) {
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

    private void createCourt(HTMLJudgment judgment){
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
    private void createRegulationStats2(List<HTMLRegulation> HTMLregulations){
        for(HTMLRegulation HTMLregulation : HTMLregulations){
            ReferencedRegulation newRegulation = new ReferencedRegulation(HTMLregulation);

            if(regulations.containsValue(newRegulation)){
                regulations.get(newRegulation.hashCode()).incremenetNumberOfReferentions();
            }else{
                regulations.put(newRegulation.hashCode(), newRegulation);
            }
        }
    }

    public ArrayList<Court> getCourtsStats() {
        return courtsStats;
    }

    public Map<Integer, ReferencedRegulation> getRegulations() {
        return regulations;
    }

    public HashMap<String, Integer> getJudgmentJudgesStats() {
        return judgmentJudgesStats;
    }

    public HashMap<String, MonthsElement> getJudgmentsStatisticsOverYears() {
        return judgmentsStatisticsOverYears;
    }

    public List<Judge> getJudgesByJudgments() {
        return judgesByJudgments;
    }
}
