package agh.cs.project.Model;

import agh.cs.project.HTMLCLasses.HTMLPareser;
import agh.cs.project.JSONClasses.JSONParser;

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

    public DataLoader(Statistics statistics) {
        targets = new ArrayList<>();
        jsonParser = new JSONParser(statistics);
        htmlPareser = new HTMLPareser(statistics);
        judgments = new HashMap<>();
        judges = new HashMap<>();
    }

    public boolean loadPaths(String path) throws IOException {
        Path directory = Paths.get(path);

        Stream<Path> paths = Files.walk(directory);
        targets = paths.filter(x -> !x.toFile().isDirectory()).filter(x -> x.toString().endsWith("html")
                || x.toString().endsWith("json")).collect(Collectors.toList());

        return true;
    }

    public void fetchAll() throws IOException {
        jsonParser.load(targets);
        jsonParser.fetchAll(judgments, judges);

        htmlPareser.load(targets);
        htmlPareser.fetchAll(judgments, judges);
    }

    public Map<String, Judge> getJudges() {
        return judges;
    }

    public HashMap<String, Judgment> getJudgments() {
        return judgments;
    }
}
