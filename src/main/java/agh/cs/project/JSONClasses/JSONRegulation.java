package agh.cs.project.JSONClasses;

import agh.cs.project.Model.AbstractRegulation;

public class JSONRegulation extends AbstractRegulation {
    public String getJournalTitle() {
        return journalTitle;
    }

    public int getJournalYear() {
        return journalYear;
    }

    public int getJournalEntry() {
        return journalEntry;
    }

    @Override
    public int getJournalNo() {
        return journalNo;
    }
}

