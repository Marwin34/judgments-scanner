package agh.cs.project.Model;

import agh.cs.project.JSONClasses.JSONJudge;

import java.util.List;

public class Judge{

    private String name;
    //private String function;
    private List<String> roles;
    private int numberOfCases;

    public Judge(String name, List<String> roles){
        this.name = name;
        this.roles = roles;
    }

    public Judge(JSONJudge judge){
        this.name = judge.getName();
        this.roles = judge.getSpecialRoles();
        numberOfCases = 1;
    }

    @Override
    public String toString(){
        StringBuilder bob = new StringBuilder(name + " roles: "); // the builder

        for(String role : roles){
            bob.append(role);
            bob.append(", ");
        }
        if(bob.lastIndexOf(",") > 0) {
            bob.deleteCharAt(bob.lastIndexOf(","));
            bob.deleteCharAt(bob.lastIndexOf(" "));
        }

        bob.append(", number of cases = ");
        bob.append(numberOfCases);

        return bob.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Judge judge = (Judge) o;

        return name != null ? name.equals(judge.name) : judge.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public int getNumberOfCases(){
        return numberOfCases;
    }

    public String getName(){
        return name;
    }

    public void incrementCase(){
        numberOfCases++;
    }
}
