package agh.cs.project;

public interface ICommand {
    String execute();

    String execute(String[] args);
}
