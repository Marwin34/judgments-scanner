package agh.cs.project;

import java.io.FileNotFoundException;

public class JudgesSystem {

    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        try {
            if (!parser.load("C:\\Users\\Public\\Documents\\")) {
                System.out.println("Cant load files!");
            } else {
                parser.fetchAll();
                System.out.println(parser.judgments.get("I C 93/13").showRubrum());
                System.out.println(parser.judgments.get("I C 93/13").showJustification());
                System.out.println(parser.getTop10Judges());
                System.out.println(parser.getCourtsStatistics());
                System.out.println(parser.getTop10Regulations());
                System.out.println(parser.getJudgesToJudgment());
                System.out.println(parser.monthStats());
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
