package agh.cs.project.JSONClasses;

import agh.cs.project.Model.AbstractJudge;
import java.util.List;

public class JSONJudge extends AbstractJudge {

    @Override
    public String getName(){
        return name;
    }

    @Override
    public List<String> getSpecialRoles(){
        return specialRoles;
    }



}
