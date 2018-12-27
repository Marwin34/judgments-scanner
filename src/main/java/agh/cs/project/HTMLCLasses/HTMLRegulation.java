package agh.cs.project.HTMLCLasses;

import agh.cs.project.Model.AbstractRegulation;

public class HTMLRegulation extends AbstractRegulation {

    public HTMLRegulation(String journalTitle,  int journalYear, int journalEntry) {
        this.journalTitle = journalTitle;

        if (journalTitle.contains("- tekst jednolity")) {
            this.journalTitle = journalTitle.replace("- tekst jednolity", "");
        }

        if (journalTitle.contains("- tekst jedn.")) {
            this.journalTitle = journalTitle.replace("- tekst jedn.", "");
        }

        if (journalTitle.contains("- t.j.")) {
            this.journalTitle = journalTitle.replace("- - t.j.", "");
        }

        while(this.journalTitle.endsWith(" ") || this.journalTitle.endsWith("."))
            this.journalTitle = this.journalTitle.substring(0, this.journalTitle.length() - 1);

        this.journalYear = journalYear;
        this.journalEntry = journalEntry;
    }

    public String getJournalTitle() {
        return journalTitle;
    }

    public int getJournalYear() {
        return journalYear;
    }

    public int getJournalEntry() {
        return journalEntry;
    }
}
