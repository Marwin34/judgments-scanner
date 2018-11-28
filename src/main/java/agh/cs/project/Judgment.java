package agh.cs.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Judgment {

    private String signature;
    private String judgmentDate;
    private String courtType;
    private ArrayList<Judge> judges;
    private String justification;

    public Judgment(JSONJudgment judgment, Map<String, Judge> judges){

        JudgeFactory judgeFactory = new JudgeFactory();

        signature = judgment.getSignature();
        judgmentDate = judgment.getJudgmentDate();
        courtType = judgment.getCourtType();
        justification = judgment.getJustification();

        this.judges = new ArrayList<Judge>();

        List<JSONJudge> JSONJudges = judgment.getJudges();

        for(JSONJudge JSONjudge : JSONJudges){
            this.judges.add(judgeFactory.create(JSONjudge, judges));
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

    public String showRubrum(){
        StringBuilder bob = new StringBuilder(signature);
        String judgmentRubrum = signature + " : " + courtType + " : " + judgmentDate + " : judges = ";

        bob.append(judgmentRubrum);

        for(Judge judge : judges) {
            bob.append(judge.toString());
            bob.append("; ");
        }

        return bob.toString();
    }

    public String showJustification(){
        return justification;
    }
}
