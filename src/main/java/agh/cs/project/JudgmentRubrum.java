package agh.cs.project;

import javafx.util.Builder;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class JudgmentRubrum {

    private String signature;
    private String judgmentDate;
    private String courtType;
    private ArrayList<Judge> judges;


    public JudgmentRubrum(String signature, String judgmentDate, String courtType, List<JSONJudge> judges){
        this.signature = signature;
        this.courtType = courtType;
        this.judgmentDate = judgmentDate;
        this.judges = new ArrayList<Judge>();
        for(JSONJudge judge : judges){
            this.judges.add(new Judge(judge));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JudgmentRubrum judgmentRubrum = (JudgmentRubrum) o;

        return signature.equals(judgmentRubrum.signature);
    }

    @Override
    public int hashCode() {
        return signature.hashCode();
    }

    @Override
    public String toString(){
        StringBuilder bob = new StringBuilder(signature);
        bob.append(" : ");
        bob.append(courtType);
        bob.append(" : ");
        bob.append(judgmentDate);
        bob.append(" : judges = ");

        for(Judge judge : judges){
            bob.append(judge.toString());
            bob.append("; ");
        }

        return bob.toString();
    }
}
