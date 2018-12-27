package agh.cs.project.HTMLCLasses;

import agh.cs.project.Model.AbstractRegulation;

public class HTMLRegulation extends AbstractRegulation {

    public HTMLRegulation(String journalTitle,  int journalYear, int journalEntry) {

        this.journalTitle = journalTitle;

        if (journalTitle.contains("- tekst jednolity")) {
            this.journalTitle = journalTitle.replace("- tekst jednolity", "");
            if (this.journalTitle.endsWith("."))
                this.journalTitle = this.journalTitle.substring(0, this.journalTitle.lastIndexOf(".") - 1).trim();
        }

        if (journalTitle.contains("- tekst jedn.")) {
            this.journalTitle = journalTitle.replace("- tekst jedn.", "");
            this.journalTitle = this.journalTitle.substring(0, this.journalTitle.lastIndexOf(".") - 1).trim();
        }

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
