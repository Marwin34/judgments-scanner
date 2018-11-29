package agh.cs.project;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class JSONParser {
    private Gson gson = new Gson();

    private List<JSONJudgment> loadedJSONJudgments;

    private Map<String, Judge> judges;

    public HashMap<String, Judgment> judgments;

    public JSONParser() {
        judgments = new HashMap<String, Judgment>();
        judges = new HashMap<String, Judge>();
        loadedJSONJudgments = new ArrayList<JSONJudgment>();
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

            judgments.put(judgment.getSignature(), new Judgment(judgment, judges));
        }
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
