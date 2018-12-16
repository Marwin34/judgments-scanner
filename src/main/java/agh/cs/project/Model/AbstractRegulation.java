package agh.cs.project.Model;

public abstract class AbstractRegulation {
    protected String journalTitle;
    protected int journalNo;
    protected int journalYear;
    protected int journalEntry;
    protected String text;

    public abstract String getJournalTitle();

    public abstract int getJournalNo();

    public abstract int getJournalYear();

    public abstract int getJournalEntry();
}
