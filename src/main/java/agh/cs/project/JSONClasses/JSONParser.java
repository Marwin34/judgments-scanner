package agh.cs.project.JSONClasses;

import agh.cs.project.Model.*;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.*;

public class JSONParser {
    private Gson gson = new Gson();
    private Statistics statistics;
    private List<IJudgment> loadedJSONJudgments;

    public JSONParser(Statistics statistics) {
        loadedJSONJudgments = new ArrayList<>();
        this.statistics = statistics;
    }

    public void load(List<Path> targets) throws FileNotFoundException {
        for (Path file : targets) {
            if (file.toString().endsWith(".json")) {
                loadedJSONJudgments.addAll(gson.fromJson(new FileReader(file.toString()), JSONTemplateClass.class).items);
            }
        }
        System.out.println("Loaded " + loadedJSONJudgments.size() + " elements from json file(s).");
    }

    public void fetchAll(HashMap<String, Judgment> judgments, Map<String, Judge> judges) {
        for (IJudgment judgment : loadedJSONJudgments) {
            judgments.put(judgment.getSignature(), new Judgment(judgment, judges));
        }

        statistics.load(loadedJSONJudgments, judges);
    }
}
