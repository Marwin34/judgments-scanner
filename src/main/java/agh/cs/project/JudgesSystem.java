package agh.cs.project;

import java.io.FileNotFoundException;

public class JudgesSystem {

    public static void main(String[] args){
        System.out.println("Hello world");

        JSONLoader loader = new JSONLoader();
        try{
            loader.load();
        }catch (FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }

    }
}
