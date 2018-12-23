package agh.cs.project.Model;

import java.util.*;

public class Statistics {

    private ArrayList<Court> courtsStats;
    private Map<Integer, ReferencedRegulation> regulations;
    private HashMap<String, Integer> judgmentJudgesStats;
    private HashMap<Integer, Integer> judgmentsOverMonths;
    private List<Judge> judgesByJudgments;
    private Map<Integer, Integer> juryStatistic;

    public Statistics() {
        courtsStats = new ArrayList<>();
        regulations = new HashMap<>();
        judgmentJudgesStats = new HashMap<>();
        judgmentsOverMonths = new HashMap<>();
        judgesByJudgments = new ArrayList<>();
        juryStatistic = new HashMap<>();
    }

    public void load(List<IJudgment> judgments, Map<String, Judge> judges) {
        convertJudgesToTop10(judges);
        for (IJudgment judgment : judgments) {
            createYearlyStatistics(judgment);
            judgesNumberToJudgment(judgment);
            createJuryStatistics(judgment);
            createCourt(judgment);
            createRegulationStats(judgment.getReferencedRegulations());
        }
    }

    private void createJuryStatistics(IJudgment judgment){
        if(juryStatistic.containsKey(judgment.numberOfJudges())){
            juryStatistic.put(judgment.numberOfJudges(), juryStatistic.get(judgment.numberOfJudges()) + 1);
        }else
            juryStatistic.put(judgment.numberOfJudges(), 1);
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

    private void judgesNumberToJudgment(IJudgment judgment) {
        judgmentJudgesStats.put(judgment.getSignature(), judgment.numberOfJudges());
    }

    private void createYearlyStatistics(IJudgment judgment) {
        String id = judgment.getJudgmentDate().split("-")[1];

        int i = Integer.parseInt(id) - 1;
        addToMonth(i);
    }

    private void addToMonth(int month) {
        if (month >= 0 && month < 12) {
            if (judgmentsOverMonths.containsKey(month)) {
                judgmentsOverMonths.put(month, judgmentsOverMonths.get(month) + 1);
            } else {
                judgmentsOverMonths.put(month, 1);
            }
        }
    }

    private void createCourt(IJudgment judgment){
        Court newCourt = new Court(judgment.getCourtType());

        for(Court court : courtsStats){
            if(court.equals(newCourt)) {
                court.incrementNumberOfJudgments();
                return;
            }
        }
        courtsStats.add(newCourt);
    }

    private void createRegulationStats(List<AbstractRegulation> referencedRegulations){
        for(AbstractRegulation regulation : referencedRegulations){
            ReferencedRegulation newRegulation = new ReferencedRegulation(regulation);

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

    public HashMap<Integer, Integer> getJudgmentsOverMonths() {
        return judgmentsOverMonths;
    }

    public List<Judge> getJudgesByJudgments() {
        return judgesByJudgments;
    }

    public Map<Integer, Integer> getJuryStatistic() {
        return juryStatistic;
    }
}
