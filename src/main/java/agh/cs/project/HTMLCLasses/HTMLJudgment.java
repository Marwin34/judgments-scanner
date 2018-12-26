package agh.cs.project.HTMLCLasses;

import agh.cs.project.Model.AbstractJudge;
import agh.cs.project.Model.AbstractRegulation;
import agh.cs.project.Model.CourtType;
import agh.cs.project.Model.IJudgment;
import agh.cs.project.Utilities.CourtTypeConverter;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class HTMLJudgment implements IJudgment {
    private String signature;
    private CourtType courtType;
    private String judgmentDate;
    private String justification;
    private List<AbstractRegulation> referencedRegulations;
    private List<AbstractJudge> judges;

    public HTMLJudgment(Document judgment) {
        Element body = judgment.body();
        judges = new ArrayList<>();
        referencedRegulations = new ArrayList<>();

        //LOAD STRING DATA
        String header = body.getElementsByClass("war_header").text();
        signature = header.substring(0, header.indexOf('-') - 1);
        judgmentDate = body.getElementsByClass("niezaznaczona").get(0).text().split(" ")[2];
        courtType = CourtTypeConverter.convert(body.getElementsByClass("niezaznaczona").get(2).text().substring(4));

        Elements justyficationElements = body.select(".niezaznaczona > .info-list-label-uzasadnienie");
        justification = justyficationElements.stream().map(Element::text).collect(Collectors.joining("\n"));

        Element judgeContainer = body.select(".niezaznaczona > .info-list-value").get(3);
        List<String> HTMLjudges = Arrays.stream(judgeContainer.html().split("<br>")).collect(Collectors.toList());

        //LOAD JUDGES
        for (String HTMLjudge : HTMLjudges) {
            addJudge(HTMLjudge);
        }

        //LOAD REFERENCED REGULATIONS
        StringBuilder bob = new StringBuilder();

        body.select(".niezaznaczona > .info-list-value").
                stream().filter(element -> element.html().contains("Dz.U")).map(Element::text).forEach(bob::append);

        String referencedText = bob.toString();

        Pattern pattern1 = Pattern.compile("\\d{4}\\snr\\s\\d+\\spoz\\s\\d+");
        Pattern pattern2 = Pattern.compile("[A-Z].*$");

        for (String test : referencedText.split("Dz\\.U\\. ")) {
            Matcher matcher1 = pattern1.matcher(test);
            Matcher matcher2 = pattern2.matcher(test);
            if (matcher1.find() && matcher2.find()) {
                String parts[] = matcher1.group().split(" ");
                int jounralYear = Integer.parseInt(parts[0]);
                int journalNo = Integer.parseInt(parts[2]);
                int journalEntry = Integer.parseInt(parts[4]);
                String journalTitle = matcher2.group();
                referencedRegulations.add(new HTMLRegulation(journalTitle, journalNo, jounralYear, journalEntry));
            }
        }
    }

    private void addJudge(String judge) {
        List<String> array = Arrays.stream(judge.split("/")).collect(Collectors.toList());
        String name = array.get(0).trim();

        List<String> roles = new ArrayList<>();

        if (array.size() > 1) {
            roles.addAll(Arrays.stream(array.get(1).split(" ")).collect(Collectors.toList()));
        } else {
            roles.add("barak");
        }
        judges.add(new HTMLJudge(name, roles));
    }

    @Override
    public int numberOfJudges(){
        return judges.size();
    }

    @Override
    public String getSignature() {
        return signature;
    }

    @Override
    public CourtType getCourtType() {
        return courtType;
    }

    @Override
    public String getJudgmentDate() {
        return judgmentDate;
    }

    @Override
    public String getJustification() {
        return justification;
    }

    @Override
    public List<AbstractJudge> getJudges() {
        return judges;
    }

    @Override
    public List<AbstractRegulation> getReferencedRegulations() {
        return referencedRegulations;
    }
}