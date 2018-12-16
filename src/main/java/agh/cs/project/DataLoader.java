package agh.cs.project;

import agh.cs.project.HTMLCLasses.HTMLPareser;
import agh.cs.project.JSONClasses.JSONParser;
import agh.cs.project.Model.Judge;
import agh.cs.project.Model.Judgment;
import agh.cs.project.Model.Statistics;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class DataLoader {

    private JSONParser jsonParser;

    private HTMLPareser htmlPareser;

    private List<Path> targets;

    private Map<String, Judge> judges;

    private HashMap<String, Judgment> judgments;

    private final Statistics statistics;

    public DataLoader(Statistics statistics){
        this.statistics = statistics;
        targets = new ArrayList<>();
        jsonParser = new JSONParser(this.statistics);
        htmlPareser = new HTMLPareser(this.statistics);
        judgments = new HashMap<>();
        judges = new HashMap<>();
    }

    public boolean loadPaths(String path) throws IOException {

        if (isEmpty(path))
            return false;

        Path directory = Paths.get(path);

        Stream<Path> paths = Files.walk(directory);
        targets = paths.filter(x -> !x.toFile().isDirectory()).filter(x -> {
            return x.toString().endsWith("html") || x.toString().endsWith("json");
        }).collect(Collectors.toList());

        System.out.println(targets.size());

        return true;
    }

    public void fetchAll() throws IOException {
        jsonParser.load(targets);
        jsonParser.fetchAll(judgments, judges);

        htmlPareser.load(targets);
        htmlPareser.fetchAll(judgments, judges);
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
}
