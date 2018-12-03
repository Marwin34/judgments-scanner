package agh.cs.project;

import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.UserInterruptException;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JudgesSystem {

    public static void main(String[] args) throws IOException {

        //TODO - observer for judges
        JSONParser parser = new JSONParser();

        try {
            if (!parser.load("C:\\Users\\Public\\Documents\\")) {
                System.out.println("Cant load files!");
            } else {
                parser.fetchAll();
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        Map<String, ICommand> commands = new HashMap<String, ICommand>();

        commands.put("topJudges", new StatisticsWrapper(parser.statisticsGenerator.getTop10Judges()));
        commands.put("topRegulations", new StatisticsWrapper(parser.statisticsGenerator.getTop10Regulations()));
        commands.put("courts", new StatisticsWrapper(parser.statisticsGenerator.getCourtsStatistics()));
        commands.put("judgesToJudgment", new StatisticsWrapper(parser.statisticsGenerator.getJudgesToJudgment()));
        commands.put("years", new StatisticsWrapper(parser.statisticsGenerator.yearStats()));
        //commands.put("month", new StatisticsWrapper(parser.statisticsGenerator.yearStats()));

        //TerminalBuilder builder = TerminalBuilder.builder();

        //Terminal terminal = builder.build();

        LineReader reader = LineReaderBuilder.builder().build();



        String prompt = "GHCI>";
        while (true) {
            String line = null;
            try {
                line = reader.readLine(prompt);
                String command = line.split(" ")[0];

                if(command.equals("exit"))
                    System.exit(1);

                String output = "";
                ICommand commandExecutor = commands.get(command);

                if(commandExecutor == null){
                    output = "Niepoprawna komenda!\n";
                }else{
                    output = commandExecutor.execute();
                }

                reader.getTerminal().writer().print(output);

            } catch (UserInterruptException e) {
                // Ignore
            } catch (EndOfFileException e) {
                return;
            }
        }
    }
        /*JSONParser parser = new JSONParser();

        try {
            if (!parser.load("C:\\Users\\Public\\Documents\\")) {
                System.out.println("Cant load files!");
            } else {
                parser.fetchAll();
                //System.out.println(parser.judgments.get("I C 93/13").showRubrum());
                //System.out.println(parser.judgments.get("I C 93/13").showJustification());
                //System.out.println(parser.statisticsGenerator.getTop10Judges());
                //System.out.println(parser.statisticsGenerator.getCourtsStatistics());
                //System.out.println(parser.statisticsGenerator.getTop10Regulations());
                //System.out.println(parser.statisticsGenerator.getJudgesToJudgment());
                //System.out.println(parser.monthStats());
                //System.out.println(parser.statisticsGenerator.monthStats());
                System.out.println(parser.statisticsGenerator.yearStats());
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
    //}*/
}
