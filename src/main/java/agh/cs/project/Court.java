package agh.cs.project;

import java.util.List;

public class Court {

    private int id;
    private String courtType;
    private List<CourtCase> courtCases;
    private String judgmentType;
    private List<Judge> judges;
    private Source source;
    private List<String> courtReporters;
    private String decision;
    private String summary;
    private String textContent;
    private List<String> legalBases;
    private List<Regulation> referencedRegulations;
    private List<String> keywords;
    private List<ReferencedCourtCase> referencedCourtCases;
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
}
