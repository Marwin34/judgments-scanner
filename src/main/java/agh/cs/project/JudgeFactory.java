package agh.cs.project;

import agh.cs.project.JSONClasses.JSONJudge;

import java.util.Map;

public class JudgeFactory {
    public Judge create(JSONJudge judge, Map<String, Judge> judges){
        if(judges.containsKey(judge.getName())){
            Judge existingJudge = judges.get(judge.getName());
            existingJudge.incrementCase();
            return existingJudge;
        }else {
            Judge newJudge = new Judge(judge);
            judges.put(judge.getName(), newJudge);
            return newJudge;
        }
    }
}
