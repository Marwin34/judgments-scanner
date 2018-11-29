package agh.cs.project;

import java.util.*;

public class Statistics {

    //TODO - Move all statistics dependencies here.
    private ArrayList<Court> courts;
    private Map<Integer, ReferencedRegulation> regulations;
    private HashMap<String, Integer> judgmentJudgesStats;
    private Map<String, Integer> judgmentsPerMonth;

    public Statistics(){
        courts = new ArrayList<Court>();
        regulations = new HashMap<Integer, ReferencedRegulation>();
        judgmentJudgesStats = new HashMap<String, Integer>();
        judgmentsPerMonth = new HashMap<String, Integer>();
    }

    public void Load(List<JSONJudgment> judgments){
        for(JSONJudgment judgment : judgments){
            createMonthlyStatistics(judgment);
        }
    }

    private void addStatistics(JSONJudgment judgment){
        judgmentJudgesStats.put(judgment.getSignature(), judgment.numberOfJudges());
    }

    private void createMonthlyStatistics(JSONJudgment judgment){
        String id = judgment.getJudgmentDate().split("-")[1];
        Integer value = judgmentsPerMonth.get(id);

        if(value == null){
            judgmentsPerMonth.put(id, 1);
        }else{
            int tmp = judgmentsPerMonth.get(id);
            judgmentJudgesStats.remove(id);
            judgmentsPerMonth.put(id, tmp + 1);
        }
    }

    public String monthStats(){
        StringBuilder bob = new StringBuilder();
        for (Map.Entry entry : judgmentsPerMonth.entrySet()){
            String toAppend = entry.getKey() + ": " + entry.getValue() + " orzeczeń.\n";
            bob.append(toAppend);
        }
        return bob.toString();
    }

    public String getJudgesToJudgment(){
        int min = 0, avg = 0, max = 0;

        min = Collections.min(judgmentJudgesStats.values());

        for(Integer value : judgmentJudgesStats.values()){
            avg += value;
        }
        avg /= judgmentJudgesStats.size();

        max = Collections.max(judgmentJudgesStats.values());

        return "Najmniejsza liczba sedziow w orzeczeniu to: " + min + "natomiast najwieksza liczba swedziow " +
                "przypadajacych na orzeczenie to: " + max + ". Srednia liczba sedziow na orzeczenie: " + avg + ".";
    }

    public String getCourtsStatistics() {
        StringBuilder bob = new StringBuilder();

        Collections.sort(courts, new Comparator<Court>() {
            public int compare(Court o1, Court o2) {
                return o2.getNumberOfJudgments() - o1.getNumberOfJudgments(); // Descending.
            }
        });

        for(Court court : courts){
            bob.append(court);
            bob.append("\n");
        }

        return bob.toString();
    }

    public String getTop10Regulations(){
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
}
