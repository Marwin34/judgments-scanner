package agh.cs.project;

import java.util.List;
import java.util.Map;

public class JSONJudgment {

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

    private JudgeFactory factory = new JudgeFactory();

    public void fetchJudges(Map<String, Judge> judges){
        for(JSONJudge judge : this.judges){
            factory.create(judge, judges);
        }
    }

    @Override
    public String toString(){
        return id + ": " + judgmentType + ", " + judgmentDate;
    }

    public String getSignature(){
        return courtCases.get(0).getCaseNumber();
    }

    public String getJudgmentDate(){
        return  judgmentDate;
    }

    public String getJustification(){
        return textContent;
    }

    public List<JSONJudge> getJudges(){
        return judges;
    }

    public String getCourtType(){
        return courtType;
    }

    public List<JSONRegulation> getReferencedRegulations() {
        return referencedRegulations;
    }
}
