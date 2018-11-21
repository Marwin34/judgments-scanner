package agh.cs.project;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JSONLoader {
    private Gson gson = new Gson();

    private List<JSONTemplateClass> cases = new ArrayList<JSONTemplateClass>();

    public JSONLoader(){
        //empty
    }

    public void load() throws FileNotFoundException{
        cases.add(gson.fromJson(new FileReader("C:\\Users\\Marcin\\Desktop\\judgments.json"), JSONTemplateClass.class));
        System.out.println(cases.get(0));
       //System.out.println(gson.fromJson(new FileReader("C:\\Users\\Marcin\\Desktop\\judgments.json"), Object.class));
    }
}
