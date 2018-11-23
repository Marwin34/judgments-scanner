package agh.cs.project;

import java.util.List;

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

    @Override
    public String toString(){
        return id + ": " + judgmentType + ", " + judgmentDate;
    }

    public String getSignature(){
        return courtCases.get(0).getCaseNumber();
    }

    public JudgmentRubrum getRubrum(){
        return new JudgmentRubrum(getSignature(), judgmentDate, courtType, judges);
    }
}
