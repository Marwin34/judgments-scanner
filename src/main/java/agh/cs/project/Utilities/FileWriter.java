package agh.cs.project.Utilities;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileWriter {

    private final String path;

    public FileWriter(String path){
        this.path = path;
    }


    public void saveToFile(String command, String output) throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);
        Charset charset = Charset.forName("UTF-8");

        String toWrite = date.toString() + ": " + command + " " + System.lineSeparator() + output;
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(path), charset, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        writer.write(toWrite, 0, toWrite.length());
        writer.close();
    }
}
