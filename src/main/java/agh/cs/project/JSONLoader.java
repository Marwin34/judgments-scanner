package agh.cs.project;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class JSONLoader {
    private Gson gson = new Gson();

    private JSONTemplateClass file;

    public JSONLoader(){
        file = new JSONTemplateClass();
    }

    public void load(String path) throws FileNotFoundException{
        file = gson.fromJson(new FileReader(path), JSONTemplateClass.class);
        System.out.println("Loaded " + file.size() + " cases.");
    }
}
