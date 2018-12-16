package agh.cs.project.JSONClasses;

import agh.cs.project.Model.Judge;
import agh.cs.project.Model.Judgment;
import agh.cs.project.Model.Statistics;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.*;

public class JSONParser {
    private Gson gson = new Gson();
    private Statistics statistics;
    private List<JSONJudgment> loadedJSONJudgments;

    public JSONParser(Statistics statistics) {
        loadedJSONJudgments = new ArrayList<>();
        this.statistics = statistics;
    }

    public boolean load(List<Path> targets) throws FileNotFoundException {
        for (Path file : targets) {
            if (file.toString().endsWith(".json")) {
                System.out.println("Load " + file.toString());
                loadedJSONJudgments.addAll(gson.fromJson(new FileReader(file.toString()), JSONTemplateClass.class).items);
            }
        }
        System.out.println("Loaded " + loadedJSONJudgments.size() + " elements from json file(s).");
        return true;
    }

    public void fetchAll(HashMap<String, Judgment> judgments, Map<String, Judge> judges) {
        for (JSONJudgment judgment : loadedJSONJudgments) {
            judgments.put(judgment.getSignature(), new Judgment(judgment, judges));
        }
        statistics.load(loadedJSONJudgments, judges);
    }
}
