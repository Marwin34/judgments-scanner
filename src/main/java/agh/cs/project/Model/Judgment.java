package agh.cs.project.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Judgment {

    private String signature;
    private String judgmentDate;
    private String courtType;
    private ArrayList<Judge> judges;
    private String justification;

    public Judgment(IJudgment judgment, Map<String, Judge> judges) {

        signature = judgment.getSignature();
        judgmentDate = judgment.getJudgmentDate();
        courtType = judgment.getCourtType();
        justification = judgment.getJustification();

        this.judges = new ArrayList<>();

        List<AbstractJudge> JSONJudges = judgment.getJudges();

        for (AbstractJudge JSONjudge : JSONJudges) {
            this.judges.add(create(JSONjudge, judges));
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
        StringBuilder bob = new StringBuilder(signature);
        String judgmentRubrum = signature + " : " + courtType + " : " + judgmentDate + " : judges = ";

        bob.append(judgmentRubrum);

        for (Judge judge : judges) {
            bob.append(judge.toString());
            bob.append("; ");
        }

        return bob.toString();
    }

    public String showJustification() {
        return justification;
    }
}
