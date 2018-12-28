package agh.cs.project;

import agh.cs.project.Commands.*;
import agh.cs.project.Model.DataLoader;
import agh.cs.project.Utilities.FileUtilities;
import agh.cs.project.Utilities.FileWriter;
import agh.cs.project.Model.Statistics;
import agh.cs.project.Utilities.TerminalHandler;
import org.jline.reader.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JudgesSystem {

    public static void main(String[] args) throws IOException {
        Statistics statistics = new Statistics();
        DataLoader loader = new DataLoader(statistics);
        FileWriter fileWriter = null;

        boolean writeMode = false;
        String inputPath = "";

        Map<String, ICommand> commands = new HashMap<>(); //Initialize commands
        commands.put("judges", new JudgesCommand(statistics));
        commands.put("judge", new JudgeCommand(loader));
        commands.put("jury", new JuryCommand(statistics));
        commands.put("regulations", new RegulationsCommand(statistics));
        commands.put("courts", new CourtsCommand(statistics));
        commands.put("months", new MonthsCommand(statistics));
        commands.put("rubrum", new RubrumCommand(loader));
        commands.put("content", new ContentCommand(loader));
        commands.put("exit", new ExitCommand());
        commands.put("help", new HelpCommand(commands));

        TerminalHandler terminalHandler = new TerminalHandler(new ArrayList<>(commands.keySet()));

        System.out.println("Welcome to judgment scanner!");

        String line;

        try {
            if (args.length >= 1) {
                if (FileUtilities.isStandardPath(args[0]) && FileUtilities.isNotEmpty(args[0])) {
                    inputPath = args[0];
                } else {
                    System.out.println("Folder with data doesn't exits or is empty.");
                    System.exit(0);
                }
            } else inputPath = ".";

            if (args.length >= 2) {
                if (FileUtilities.isStandardPath(args[1])) {

                    String pathToDirectory = ".";

                    if (args[1].lastIndexOf("/") > -1) {
                        pathToDirectory = args[1].substring(0, args[1].lastIndexOf("/"));
                    }

                    if (FileUtilities.checkIfFileExists(new File(pathToDirectory))
                            && FileUtilities.checkIfFileIsDirectory(new File(pathToDirectory))) {
                        writeMode = true;
                        fileWriter = new FileWriter(args[1]);
                    } else {
                        System.out.println("Directory not exists " + pathToDirectory + "!");
                    }
                } else {
                    System.out.println("Unsupported path to file " + args[1] + ".");
                }
            }

            if (!loader.loadPaths(inputPath)) {
                System.out.println("Cant load files!");
                System.exit(0);
            } else {
                try {
                    loader.fetchAll();
                } catch (FileNotFoundException ex) {
                    terminalHandler.write(ex.getMessage());
                }

                while (true) {
                    try {
                        line = terminalHandler.read();

                        Pattern universalPattern = Pattern.compile("(\"\\w+\\s?\\w+\"|\\w+|\".*\")");
                        Matcher universalMatcher = universalPattern.matcher(line);

                        List<String> words = new ArrayList<>();
                        while (universalMatcher.find()) {
                            words.add(universalMatcher.group().replaceAll("\"", " ").trim());
                        }

                        String output = "";
                        if (words.size() >= 1) {

                            String command = words.get(0);
                            ICommand commandExecutor = commands.getOrDefault(command, new CommandNotFound());
                            if (words.size() >= 2)
                                output = commandExecutor.execute(words.subList(1, words.size()));
                            else{
                                output = commandExecutor.execute();
                            }
                        }

                        //TODO jansi format
                        terminalHandler.write(output);

                        if (writeMode)
                            fileWriter.saveToFile(line, output);

                    } catch (UserInterruptException e) {
                        // Ignore
                    } catch (EndOfFileException e) {
                        return;
                    } catch (IOException e) {
                        System.out.println("Erorr " + e.getMessage() + ": cant save to file!");
                    }
                }
            }

        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            System.exit(0);
        }
    }
}
