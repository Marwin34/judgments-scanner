package agh.cs.project;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONParser {
    private Gson gson = new Gson();

    private List<JSONJudgment> loadedJSONJudgments;

    public Map<String, Judge> judges;

    public HashMap<String, Judgment> judgments;

    public JSONParser(){
        judgments = new HashMap<String, Judgment>();
        judges = new HashMap<String, Judge>();
        loadedJSONJudgments = new ArrayList<JSONJudgment>();
    }

    public boolean load(String path) throws FileNotFoundException{

        File directory = new File(path + "\\json");

        if(isEmpty(path))
            return false;

        for(File file : directory.listFiles()){
            if(file.getName().endsWith(".json")){
                loadedJSONJudgments.addAll(gson.fromJson(new FileReader(file.toString()), JSONTemplateClass.class).items);
            }
        }
        System.out.println("Loaded " + loadedJSONJudgments.size() + " elements from json file(s)." );

        return true;
    }

    public void fetchAll(){
        for (JSONJudgment judgment : loadedJSONJudgments){
            judgments.put(judgment.getSignature(), new Judgment(judgment, judges));
        }
    }

    private boolean isEmpty(String path){
        File directory = new File(path + "\\json");

        return (checkIfFileExists(directory) && checkIfFileIsDirectory(directory) && checkIfDirectoryIsEmpty(directory));
    }

    private boolean checkIfFileExists(File file) {
        return file.exists();
    }

    private boolean checkIfFileIsDirectory(File file) {
        return file.isDirectory();
    }

    private boolean checkIfDirectoryIsEmpty(File dir) {
       if(checkIfFileExists(dir)) {
           return dir.listFiles().length == 0;
       }

       return false;
    }
}
