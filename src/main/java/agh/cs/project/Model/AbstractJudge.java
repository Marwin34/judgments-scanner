package agh.cs.project.Model;

import java.util.List;

public abstract class AbstractJudge {
    protected String name;
    protected String function;
    protected List<String> specialRoles;

    public abstract String getName();

    public abstract List<String> getSpecialRoles();
}
