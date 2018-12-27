package agh.cs.project.Utilities;

import org.jline.reader.Completer;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;
import java.util.List;

public class TerminalHandler {
    private final LineReader reader;
    private final String prompt;
    private final Terminal terminal;

    public TerminalHandler(List<String> commands) throws IOException{
        Completer completer = new StringsCompleter(commands);

        terminal = TerminalBuilder
                .builder()
                .system(true)
                .build();

        LineReaderBuilder builder = LineReaderBuilder.builder()
                .terminal(terminal)
                .completer(completer)
                .appName("Judgment scanner");
         reader = builder.build();

         prompt = "> ";
    }

    public void write(String message) {
        terminal.writer().print(message);
    }

    public String read() {
        return reader.readLine(prompt);
    }
}
