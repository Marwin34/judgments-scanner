package agh.cs.project.JSONClasses;

import agh.cs.project.Model.AbstractJudge;
import agh.cs.project.Model.AbstractRegulation;
import agh.cs.project.Model.IJudgment;
import agh.cs.project.Model.CourtType;

import java.util.ArrayList;
import java.util.List;

public class JSONJudgment implements IJudgment {

    private CourtType courtType;
    private List<JSONCourtCase> courtCases;
    private List<JSONJudge> judges;
    private String textContent;
    private List<JSONRegulation> referencedRegulations;
    private String judgmentDate;

    public String getSignature(){
        return courtCases.get(0).getCaseNumber();
    }

    @Override
    public String getJudgmentDate(){
        return  judgmentDate;
    }

    @Override
    public String getJustification(){
        return textContent;
    }

    @Override
    public List<AbstractJudge> getJudges(){
        return new ArrayList<>(judges);
    }

    @Override
    public CourtType getCourtType(){
        return courtType;
    }

    @Override
    public List<AbstractRegulation> getReferencedRegulations() {
        return new ArrayList<>(referencedRegulations);
    }

    @Override
    public int numberOfJudges(){
        return judges.size();
    }

}
