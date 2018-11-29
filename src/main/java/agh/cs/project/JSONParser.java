package agh.cs.project;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class JSONParser {
    private Gson gson = new Gson();

    private List<JSONJudgment> loadedJSONJudgments;

    private ArrayList<Court> courts;

    private Map<String, Judge> judges;

    private Map<Integer, ReferencedRegulation> regulations;

    public HashMap<String, Judgment> judgments;

    private HashMap<String, Integer> judgmentJudgesStats;

    private Map<String, Integer> judgmentspPerMonth;


    public JSONParser() {
        judgments = new HashMap<String, Judgment>();
        judges = new HashMap<String, Judge>();
        loadedJSONJudgments = new ArrayList<JSONJudgment>();
        courts = new ArrayList<Court>();
        regulations = new HashMap<Integer, ReferencedRegulation>();
        judgmentJudgesStats = new HashMap<String, Integer>();
        judgmentspPerMonth = new HashMap<String, Integer>();
    }

    public boolean load(String path) throws FileNotFoundException {

        File directory = new File(path + "\\json");

        if (isEmpty(path))
            return false;

        for (File file : directory.listFiles()) {
            if (file.getName().endsWith(".json")) {
                loadedJSONJudgments.addAll(gson.fromJson(new FileReader(file.toString()), JSONTemplateClass.class).items);
            }
        }
        System.out.println("Loaded " + loadedJSONJudgments.size() + " elements from json file(s).");

        return true;
    }

    public void fetchAll() {
        for (JSONJudgment judgment : loadedJSONJudgments) {
            createCourt(judgment.getCourtType(), courts);

            addStatistics(judgment);

            createMonthlyStatistics(judgment);

            for(JSONRegulation JSONregulation : judgment.getReferencedRegulations()){
                createRegulation(JSONregulation, regulations);
            }

            judgments.put(judgment.getSignature(), new Judgment(judgment, judges));
        }
    }

    private void addStatistics(JSONJudgment judgment){
        judgmentJudgesStats.put(judgment.getSignature(), judgment.numberOfJudges());
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

    private void createMonthlyStatistics(JSONJudgment judgment){
        String id = judgment.getJudgmentDate().split("-")[1];
        Integer value = judgmentspPerMonth.get(id);

        if(value == null){
            judgmentspPerMonth.put(id, 1);
        }else{
            int tmp = judgmentspPerMonth.get(id);
            judgmentJudgesStats.remove(id);
            judgmentspPerMonth.put(id, tmp + 1);
        }
    }

    public String monthStats(){
        StringBuilder bob = new StringBuilder();
        for (Map.Entry entry : judgmentspPerMonth.entrySet()){
            String toAppend = entry.getKey() + ": " + entry.getValue() + " orzeczeń.\n";
            bob.append(toAppend);
        }
        return bob.toString();
    }

    public String getTop10Judges() {
        StringBuilder bob = new StringBuilder();

        List<Judge> judgesByJudgments = new ArrayList<Judge>(judges.values());
        Collections.sort(judgesByJudgments, new Comparator<Judge>() {
            public int compare(Judge o1, Judge o2) {
                return o2.getNumberOfCases() - o1.getNumberOfCases(); // Descending.
            }
        });

        for (int i = 0; i < 10; i++) {
            Judge judge = judgesByJudgments.get(i);
            String judgeStats = i + 1 + ": " + judge.getName() + ": " + judge.getNumberOfCases() + "\n";
            bob.append(judgeStats);
        }
        return bob.toString();
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

    private void createCourt(String courtType, ArrayList<Court> existingCourts){
        Court newCourt = new Court(courtType);

        for(Court court : existingCourts){
            if(court.equals(newCourt)) {
                court.incrementNumberOfJudgments();
                return;
            }
        }
        existingCourts.add(newCourt);
    }

    private void createRegulation(JSONRegulation regulation, Map<Integer, ReferencedRegulation> existingRegulations){
        ReferencedRegulation newRegulation = new ReferencedRegulation(regulation);

        if(existingRegulations.containsValue(newRegulation)){
            existingRegulations.get(newRegulation.hashCode()).incremenetNumberOfReferentions();
        }else{
            existingRegulations.put(newRegulation.hashCode(), newRegulation);
        }
    }

    private boolean isEmpty(String path) {
        File directory = new File(path + "\\json");

        return (checkIfFileExists(directory) && checkIfFileIsDirectory(directory) && checkIfDirectoryIsEmpty(directory));
    }

    private boolean checkIfFileExists(File file) {
        return file.exists();
    }

    private boolean checkIfFileIsDirectory(File file) {
        return file.isDirectory();
    }

    private boolean checkIfDirectoryIsEmpty(File dir) {
        if (checkIfFileExists(dir)) {
            return dir.listFiles().length == 0;
        }

        return false;
    }
}
