package agh.cs.project.Commands;

public interface ICommand {
    String execute();

    String execute(String[] args);

}
