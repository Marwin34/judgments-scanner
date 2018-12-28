package agh.cs.project.Model;

public class ReferencedRegulation {

    private String journalTitle;
    private int journalYear;
    private int journalEntry;
    private int journalNo;
    private int numberOfReferentions;

    public ReferencedRegulation(AbstractRegulation regulation) {
        journalTitle = regulation.getJournalTitle();
        journalYear = regulation.getJournalYear();
        journalEntry = regulation.getJournalEntry();
        journalNo = regulation.getJournalNo();

        numberOfReferentions = 1;
    }

    public void incremenetNumberOfReferentions() {
        numberOfReferentions++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReferencedRegulation that = (ReferencedRegulation) o;

        return journalTitle != null ? journalTitle.equals(that.journalTitle) : that.journalTitle == null;
    }

    @Override
    public int hashCode() {
        int result = journalYear;
        result = 31 * result + journalEntry;
        result = 31 * result + journalNo;
        return result;
    }

    @Override
    public String toString() {
        return String.format("%.100s", journalTitle) + (journalTitle.length() > 100 ? "..." : "");
    }

    public int getNumberOfReferentions() {
        return numberOfReferentions;
    }

    public String getJournalTitle() {
        return journalTitle;
    }
}
