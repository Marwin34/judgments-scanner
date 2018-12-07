package agh.cs.project;

import agh.cs.project.Commands.*;
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

            if (!parser.load("json")) {
                System.out.println("Cant load files!");
                System.exit(0);
            } else {
                parser.fetchAll();

                Map<String, ICommand> commands = new HashMap<>();

                Statistics statistics = new Statistics();
                statistics.load(parser.getLoadedJSONJudgments(), parser.getJudges());

                commands.put("topJudges", new JudgesStatistics(statistics));
                commands.put("topRegulations", new RegulationStatistics(statistics));
                commands.put("courts", new CourtStatistics(statistics));
                commands.put("judgesToJudgment", new JudgmentStatistics(statistics));
                commands.put("years", new TimeStatistics(statistics));
                commands.put("rubrum", new DisplayRubrums(parser));
                commands.put("justification", new DisplayJustifications(parser));

                Terminal terminal = TerminalBuilder.builder().system(true).build();
                LineReaderBuilder builder = LineReaderBuilder.builder().terminal(terminal);
                LineReader reader = builder.build();
                String prompt = "<*>";

                while (true) {
                    String line = null;
                    try {
                        line = reader.readLine(prompt);
                        String command = line.split(" ")[0];

                        if(command.equals("exit"))
                            System.exit(1);

                        ICommand commandExecutor = commands.getOrDefault(command, new CommandNotFound());
                        String output = commandExecutor.execute();

                        terminal.writer().print(output);

                    } catch (UserInterruptException e) {
                        // Ignore
                    } catch (EndOfFileException e) {
                        return;
                    }
                }
            }

        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            System.exit(0);
        }
    }
}
