package agh.cs.project.Model;

public abstract class AbstractRegulation {
    protected String journalTitle;
    protected int journalYear;
    protected int journalNo;
    protected int journalEntry;
    protected String text;

    public abstract String getJournalTitle();

    public abstract int getJournalYear();

    public abstract int getJournalEntry();

    public int getJournalNo() {
        return journalNo;
    }
}
