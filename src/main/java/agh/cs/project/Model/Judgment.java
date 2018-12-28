package agh.cs.project.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Judgment {

    private String signature;
    private String judgmentDate;
    private CourtType courtType;
    private ArrayList<Judge> judges;
    private String justification;

    public Judgment(IJudgment judgment, Map<String, Judge> judges) {

        signature = judgment.getSignature();
        judgmentDate = judgment.getJudgmentDate();
        courtType = judgment.getCourtType();
        justification = judgment.getJustification();

        this.judges = new ArrayList<>();

        List<AbstractJudge> abstractJudges = judgment.getJudges();

        for (AbstractJudge abstractJudge : abstractJudges) {
            this.judges.add(create(abstractJudge, judges));
        }
    }

    private Judge create(AbstractJudge judge, Map<String, Judge> judges) {
        if (judges.containsKey(judge.getName())) {
            Judge existingJudge = judges.get(judge.getName());
            existingJudge.incrementCase();
            return existingJudge;
        } else {
            Judge newJudge = new Judge(judge);
            judges.put(judge.getName(), newJudge);
            return newJudge;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Judgment judgment = (Judgment) o;

        return signature.equals(judgment.signature);
    }

    @Override
    public int hashCode() {
        return signature.hashCode();
    }

    public String showRubrum() {

        return String.format("%s %-15s%n", "Sygnatura:", signature) +
                String.format("%s %-15s%n", "Typ sądu:", courtType.toString()) +
                String.format("%s %-15s%n", "Data wydania:", judgmentDate) +
                String.format("%s %-15s%n", "Skład sędziowski:",
                        judges.stream()
                                .map(Judge::toString)
                                .collect(Collectors.joining("; ")));
    }

    public String showJustification() {
        return justification;
    }
}
