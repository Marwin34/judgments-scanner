package agh.cs.project.Model;

import java.util.List;

public class Judge{

    private String name;
    private List<String> roles;
    private int numberOfCases;

    public Judge(String name, List<String> roles){
        this.name = name;
        this.roles = roles;
        numberOfCases = 1;
    }

    public Judge(AbstractJudge judge){
        this.name = judge.getName();
        this.roles = judge.getSpecialRoles();
        numberOfCases = 1;
    }

    @Override
    public String toString(){
        StringBuilder bob = new StringBuilder(name); // the builder

        if(roles.size() > 0){
            bob.append(" (");
            for(String role : roles){
                if(role.equals("PRESIDING_JUDGE"))
                    bob.append("przewodniczacy składu sędziowskiego");
                else if(role.equals("REPORTING_JUDGE"))
                    bob.append("sędzia sprawozdawca");
                else if(role.equals("REASONS_FOR_JUDGMENT_AUTHOR"))
                    bob.append("autor uzasadnienia");
                else
                    bob.append(role);
                bob.append(", ");
            }
            if(bob.lastIndexOf(",") > 0) {
                bob.deleteCharAt(bob.lastIndexOf(","));
                bob.deleteCharAt(bob.lastIndexOf(" "));
            }
            bob.append(")");
        }

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
