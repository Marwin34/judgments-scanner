package agh.cs.project;

import java.util.List;

public class Judge {

    private String name;
    //private String function;
    private List<String> roles;
    private int numberOfCases; // TODO add factory

    public Judge(String name, List<String> roles){
        this.name = name;
        this.roles = roles;
    }

    public Judge(JSONJudge judge){
        this.name = judge.getName();
        this.roles = judge.getSpecialRoles();
    }

    @Override
    public String toString(){
        StringBuilder bob = new StringBuilder(name + " roles: "); // the builder

        for(String role : roles){
            bob.append(role);
            bob.append(", ");
        }
        bob.deleteCharAt(bob.lastIndexOf(","));
        bob.deleteCharAt(bob.lastIndexOf(" "));

        return bob.toString();
    }
}
