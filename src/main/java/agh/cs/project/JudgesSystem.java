package agh.cs.project;

import agh.cs.project.Commands.*;
import agh.cs.project.Model.DataLoader;
import agh.cs.project.Model.Statistics;
import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.UserInterruptException;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JudgesSystem {

    public static void main(String[] args) throws IOException {
        try {

            String inputPath;
            String outputPath;

            if(args.length >= 1){
                inputPath = args[0];
            } else inputPath = ".";


            if (args.length >= 2){
                outputPath = args[1];
            }else outputPath = ".";

            //TODO naprawic porownywanie judgment i usunac judgment.signature
            Terminal terminal = TerminalBuilder.builder().system(true).build();
            LineReaderBuilder builder = LineReaderBuilder.builder().terminal(terminal);
            LineReader reader = builder.build();
            String prompt = "<*>";

            String line;
            Statistics statistics = new Statistics();
            DataLoader loader = new DataLoader(statistics);

            if (!loader.loadPaths(inputPath)) {
                System.out.println("Cant loadPaths files!");
                System.exit(0);
            } else {
                try{
                    loader.fetchAll();
                }catch (FileNotFoundException ex){
                    terminal.writer().print(ex.getMessage());
                }

                Map<String, ICommand> commands = new HashMap<>();


                //TODO add judge command, add jury command, add months command
                commands.put("judges", new JudgesStatistics(statistics));
                commands.put("regulations", new RegulationStatistics(statistics));
                commands.put("courts", new CourtStatistics(statistics));
                commands.put("judgesToJudgment", new JudgmentStatistics(statistics));
                commands.put("years", new TimeStatistics(statistics));
                commands.put("rubrum", new DisplayRubrums(loader));
                commands.put("content", new DisplayJustifications(loader));
                commands.put("exit", new ExitCommand());
                commands.put("help", new HelpCommand(commands));

                while (true) {
                    try {
                        line = reader.readLine(prompt);
                        Pattern pattern = Pattern.compile("^\\w*");
                        Matcher commandMatcher = pattern.matcher(line);
                        Pattern pattern2 = Pattern.compile("\"[^,]*\"");
                        Matcher argsMatcher = pattern2.matcher(line);

                        String output;

                        if(commandMatcher.find()){
                            String command = commandMatcher.group();
                            ICommand commandExecutor = commands.getOrDefault(command, new CommandNotFound());

                            List<String> arguments = new ArrayList<>();

                            while(argsMatcher.find()){
                                arguments.add(argsMatcher.group());
                            }

                            if(arguments.size() > 0){
                                output = commandExecutor.execute(arguments);
                            }
                            else{
                                output = commandExecutor.execute();
                            }
                        }else{
                            output = "Nie znaleziono komendy! " + line;
                        }

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
