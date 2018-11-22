package agh.cs.project;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JSONLoader {
    private Gson gson = new Gson();

    private List<Court> loadedObjects;

    public JSONLoader(){
        loadedObjects = new ArrayList<Court>();
    }

    public void load(String path) throws FileNotFoundException{

        File directory = new File(path + "\\json");
        try{
            for(File file : directory.listFiles()){
                if(file.toString().matches("^.*\\.json$")){
                    loadedObjects.addAll(gson.fromJson(new FileReader(file.toString()), JSONTemplateClass.class).items);
                }
            }
        }catch (NullPointerException ex){
                System.out.println(ex.getMessage() + " " + ex.getStackTrace());
        }

        displayContent();
        System.out.println("Count of elements: " + loadedObjects.size());
    }

    public void displayContent(){
        for(Court object : loadedObjects){
            System.out.println(object);
        }
    }
}
