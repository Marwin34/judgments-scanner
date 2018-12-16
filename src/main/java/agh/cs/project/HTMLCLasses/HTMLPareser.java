package agh.cs.project.HTMLCLasses;

import agh.cs.project.Model.Judge;
import agh.cs.project.Model.Judgment;
import agh.cs.project.Model.Statistics;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HTMLPareser {
    private Statistics statistics;

    private List<HTMLJudgment> loadedHTMLJudgmesnts;

    public HTMLPareser(Statistics statistics) {
        this.statistics = statistics;
        loadedHTMLJudgmesnts = new ArrayList<>();
    }

    public boolean load(List<Path> targets) throws IOException {
        for (Path file : targets) {
            if (file.toString().endsWith(".html")) {
                System.out.println("Load " + file.toString());
                loadedHTMLJudgmesnts.add(new HTMLJudgment(Jsoup.parse(file.toFile(), "UTF-8")));
            }
        }
        System.out.println("Loaded " + loadedHTMLJudgmesnts.size() + " elements from html file(s).");
        return true;
    }

    public void fetchAll(HashMap<String, Judgment> judgments, Map<String, Judge> judges) {
        for (HTMLJudgment judgment : loadedHTMLJudgmesnts) {
            judgments.put(judgment.getSignature(), new Judgment(judgment, judges));
        }
        statistics.loadHTML(loadedHTMLJudgmesnts, judges);
    }

    private String getSignature(String text){
        return text.substring(0, text.indexOf('-') - 1);
    }
}
