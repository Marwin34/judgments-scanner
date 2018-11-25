package agh.cs.project;

import java.util.Map;

public class JudgeFactory {
    public void create(JSONJudge judge, Map<String, Judge> judges){
        if(judges.containsKey(judge.getName())){
            Judge existingJudge = judges.get(judge.getName());
            existingJudge.incrementCase();
        }else {
            judges.put(judge.getName(), new Judge(judge));
        }
    }
}
