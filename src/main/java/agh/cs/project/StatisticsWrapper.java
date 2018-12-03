package agh.cs.project;

public class StatisticsWrapper implements ICommand{

    String message;

    public StatisticsWrapper(String message){
        this.message = message;
    }

    public String execute() {
        return message;
    }

    public String execute(String[] args) {
        return null;
    }
}
