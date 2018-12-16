package agh.cs.project.Model;

import java.util.List;

public interface IJudgment {
    public int numberOfJudges();

    public String getSignature();

    public String getCourtType();

    public String getJudgmentDate();

    public String getJustification();

    public List<AbstractRegulation> getReferencedRegulations();

    public List<AbstractJudge> getJudges();
}
