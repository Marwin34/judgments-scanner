package agh.cs.project;

import java.util.List;

public class JSONTemplateClass {

    private List<Case> items;

    @Override
    public String toString(){
        if(items != null)
            return items.get(0).toString();
        else return "null :(";
    }
}
