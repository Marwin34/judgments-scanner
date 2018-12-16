package agh.cs.project.JSONClasses;

import agh.cs.project.Model.AbstractJudge;
import agh.cs.project.Model.AbstractRegulation;
import agh.cs.project.Model.IJudgment;

import java.util.ArrayList;
import java.util.List;

public class JSONJudgment implements IJudgment {

    private int id;
    private String courtType;
    private List<JSONCourtCase> courtCases;
    private String judgmentType;
    private List<JSONJudge> judges;
    private JSONSource source;
    private List<String> courtReporters;
    private String decision;
    private String summary;
    private String textContent;
    private List<String> legalBases;
    private List<JSONRegulation> referencedRegulations;
    private List<String> keywords;
    private List<JSONReferencedCourtCase> referencedCourtCases;
    private String receiptDate;
    private String meansOfAppeal;
    private String judgmentResult;
    private List<String> lowerCourtJudges;
    private List<String> dissentingOptions;
    private String judgmentDate;
    
    @Override
    public String toString(){
        return id + ": " + judgmentType + ", " + judgmentDate;
    }

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
    public String getCourtType(){
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
