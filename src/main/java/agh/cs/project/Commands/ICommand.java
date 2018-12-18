package agh.cs.project.Commands;

import java.util.List;

public interface ICommand {
    String execute();

    String execute(List<String> args);

    String description();
}
