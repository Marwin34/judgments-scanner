package agh.cs.project;

import agh.cs.project.Commands.*;
import agh.cs.project.Model.DataLoader;
import agh.cs.project.Model.Statistics;
import org.jline.reader.*;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JudgesSystem {

    public static void main(String[] args) throws IOException {
        Statistics statistics = new Statistics();
        DataLoader loader = new DataLoader(statistics);

        boolean writeMode = false;
        String inputPath = "";
        String outputPath = "";

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

        Completer completer = new StringsCompleter(commands.keySet());
        Terminal terminal = TerminalBuilder.builder().system(true).build();
        LineReaderBuilder builder = LineReaderBuilder.builder()
                .terminal(terminal)
                .completer(completer)
                .appName("Judgment scanner");
        LineReader reader = builder.build();
        String prompt = "<*>";

        String line;



        System.out.println("Welcome to judgment scanner!");

        try {
            if (args.length >= 1) {
                if(isStandardPath(args[0]) && isNotEmpty(args[0])){
                    inputPath = args[0];
                }else{
                    System.out.println("Folder with data doesn't exits or is empty.");
                    System.exit(0);
                }
            } else inputPath = ".";

            if (args.length >= 2) {
                if (isStandardPath(args[1])) {

                    String pathToDirectory = ".";

                    if (args[1].lastIndexOf("/") > -1) {
                        pathToDirectory = args[1].substring(0, args[1].lastIndexOf("/"));
                    }

                    if (checkIfFileExists(new File(pathToDirectory)) && checkIfFileIsDirectory(new File(pathToDirectory))){
                        writeMode = true;
                        outputPath = args[1];
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
                    terminal.writer().print(ex.getMessage());
                }

                while (true) {
                    try {
                        line = reader.readLine(prompt);
                        Pattern pattern = Pattern.compile("^\\w*");
                        Matcher commandMatcher = pattern.matcher(line);
                        Pattern pattern2 = Pattern.compile("\"[^,]*\"");
                        Matcher argsMatcher = pattern2.matcher(line);

                        String output = "";

                        String command = "";
                        List<String> arguments = new ArrayList<>();

                        if (commandMatcher.find()) {
                            command = commandMatcher.group();
                            ICommand commandExecutor = commands.getOrDefault(command, new CommandNotFound());

                            while (argsMatcher.find()) {
                                arguments.add(argsMatcher.group());
                            }

                            if (arguments.size() > 0) {
                                output = commandExecutor.execute(arguments);
                            } else {
                                output = commandExecutor.execute();
                            }
                        } else {
                            output = "Specify command. To list available commands use help." + line;
                        }

                        //TODO jansi format
                        terminal.writer().print(output);

                        if (writeMode) {
                            saveToFile(outputPath, command, String.join(", ", arguments), output);
                        }

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

    private static void saveToFile(String outputPath, String command, String arguments, String output) throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);
        Charset charset = Charset.forName("UTF-8");

        String toWrite = date.toString() + " " + command + " " + arguments + " " + System.lineSeparator() + output;
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputPath), charset, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        writer.write(toWrite, 0, toWrite.length());
        writer.close();
    }

    private static boolean isStandardPath(String path){
        return (path.matches("^(\\./)?(\\w+/{1})*\\w+\\.txt$")) ||(path.matches("\\w*"));
    }

    private static boolean isNotEmpty(String path) {
        File directory = new File(path);

        return (checkIfFileExists(directory) && checkIfFileIsDirectory(directory) && !checkIfDirectoryIsEmpty(directory));
    }

    private static boolean checkIfFileExists(File file) {
        return file.exists();
    }

    private static boolean checkIfFileIsDirectory(File file) {
        return file.isDirectory();
    }

    private static boolean checkIfDirectoryIsEmpty(File dir) {
        if (checkIfFileExists(dir)) {
            return dir.listFiles().length == 0;
        }

        return false;
    }
}
