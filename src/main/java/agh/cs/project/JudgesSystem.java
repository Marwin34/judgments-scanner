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
        //TODO - zapoznaj sie z MVC i command
        JSONParser parser = new JSONParser();

        try {
            if (!parser.load("C:\\Users\\Public\\Documents\\")) {
                System.out.println("Cant load files!");
                System.exit(0);
            } else {
                parser.fetchAll();
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            System.exit(0);
        }

        Map<String, ICommand> commands = new HashMap<String, ICommand>();

        commands.put("topJudges", new JudgesStatistics());
        commands.put("topRegulations", new RegulationStatistics());
        commands.put("courts", new CourtStatistics());
        commands.put("judgesToJudgment", new JudgmentStatistics());
        commands.put("years", new TimeStatistics());

        //TerminalBuilder builder = TerminalBuilder.builder();

        //Terminal terminal = builder.build();

        LineReader reader = LineReaderBuilder.builder().build();

        String prompt = "<*>";
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
