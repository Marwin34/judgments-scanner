package agh.cs.project;

import java.io.FileNotFoundException;

public class JudgesSystem {

    public static void main(String[] args){
        JSONParser parser = new JSONParser();
        try{
            if(!parser.load("C:\\Users\\Public\\Documents\\")){
                System.out.println("Cant load files!");
            }else {
                parser.fetchAll();
                System.out.println(parser.rubrums.get("I C 93/13"));
            }
        }catch (FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }
}
