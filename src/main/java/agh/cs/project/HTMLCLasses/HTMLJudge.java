package agh.cs.project.HTMLCLasses;

import agh.cs.project.Model.AbstractJudge;

import java.util.List;

public class HTMLJudge extends AbstractJudge {

    private String name;
    private List<String> specialRoles;

    public HTMLJudge(String name, List<String> specialRoles){
        this.name = name;
        this.specialRoles = specialRoles;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<String> getSpecialRoles() {
        return specialRoles;
    }
}
