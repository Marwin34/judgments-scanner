package agh.cs.project;

import java.io.FileNotFoundException;

public class JudgesSystem {

    public static void main(String[] args){
        JSONLoader loader = new JSONLoader();
        try{
            loader.load("C:\\Users\\Public\\Documents\\");
        }catch (FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }
}
