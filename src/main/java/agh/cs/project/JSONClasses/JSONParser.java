package agh.cs.project.JSONClasses;

import agh.cs.project.Model.Judge;
import agh.cs.project.Model.Judgment;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class JSONParser {
    private Gson gson = new Gson();

    protected List<JSONJudgment> loadedJSONJudgments;

    private Map<String, Judge> judges;

    private HashMap<String, Judgment> judgments;

    public JSONParser() {
        judgments = new HashMap<>();
        judges = new HashMap<>();
        loadedJSONJudgments = new ArrayList<>();
    }

    public boolean load(String path) throws FileNotFoundException {
        if (isEmpty(path))
            return false;

        File directory = new File(path);

        for (File file : directory.listFiles()) {
            if (file.getName().endsWith(".json")) {
                System.out.println("Load " + file.getName());
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

    public Map<String, Judge> getJudges() {
        return judges;
    }

    public HashMap<String, Judgment> getJudgments() {
        return judgments;
    }

    public List<JSONJudgment> getLoadedJSONJudgments() {
        return loadedJSONJudgments;
    }
}
