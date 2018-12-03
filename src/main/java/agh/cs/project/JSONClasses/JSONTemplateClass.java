package agh.cs.project.JSONClasses;

import java.util.ArrayList;
import java.util.List;

public class JSONTemplateClass {

    public List<JSONJudgment> items = new ArrayList<JSONJudgment>();

    public int size(){
        return items.size();
    }
}
