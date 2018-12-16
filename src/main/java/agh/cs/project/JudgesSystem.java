package agh.cs.project;

import agh.cs.project.Commands.*;
import agh.cs.project.Model.Statistics;
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
        try {
            //TODO komendy z argumentami
            //TODO naprawic porownywanie judgment i usunac judgment.signature
            Terminal terminal = TerminalBuilder.builder().system(true).build();
            LineReaderBuilder builder = LineReaderBuilder.builder().terminal(terminal);
            LineReader reader = builder.build();
            String prompt = "<*>";

            terminal.writer().print("Podaj sciezke do folderu aby wycztac jego zawartosc. \n");
            String line = null;
            line = reader.readLine(prompt);

            Statistics statistics = new Statistics();
            DataLoader loader = new DataLoader(statistics);


            if (!loader.loadPaths(line)) {
                System.out.println("Cant loadPaths files!");
                System.exit(0);
            } else {
                try{
                    loader.fetchAll();
                }catch (FileNotFoundException ex){
                    terminal.writer().print(ex.getMessage());
                }

                Map<String, ICommand> commands = new HashMap<>();

                commands.put("topJudges", new JudgesStatistics(statistics));
                commands.put("topRegulations", new RegulationStatistics(statistics));
                commands.put("courts", new CourtStatistics(statistics));
                commands.put("judgesToJudgment", new JudgmentStatistics(statistics));
                commands.put("years", new TimeStatistics(statistics));
                commands.put("rubrum", new DisplayRubrums(loader));
                commands.put("justification", new DisplayJustifications(loader));
                commands.put("help", new HelpCommand(commands));

                while (true) {
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
