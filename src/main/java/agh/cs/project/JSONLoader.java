package agh.cs.project;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JSONLoader {
    private Gson importer = new Gson();

    private List<Case> cases = new ArrayList<Case>();

    public JSONLoader(){
        //empty
    }

    public void load(){
        try{
            cases.add(importer.fromJson(new FileReader("C:\\Users\\Marcin\\Desktop\\dziennik.json"), Case.class));
        }catch (FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }
}
