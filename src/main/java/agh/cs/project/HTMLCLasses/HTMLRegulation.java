package agh.cs.project.HTMLCLasses;

import agh.cs.project.Model.AbstractRegulation;

public class HTMLRegulation extends AbstractRegulation {

    public HTMLRegulation(String journalTitle, int journalNo, int journalYear, int journalEntry) {
        this.journalTitle = journalTitle;
        this.journalNo = journalNo;
        this.journalYear = journalYear;
        this.journalEntry = journalEntry;
    }

    public String getJournalTitle() {
        return journalTitle;
    }

    public int getJournalNo() {
        return journalNo;
    }

    public int getJournalYear() {
        return journalYear;
    }

    public int getJournalEntry() {
        return journalEntry;
    }
}
