package agh.cs.project.Commands;

public class CommandNotFound implements ICommand {
    @Override
    public String execute() {
        return "Nieznana komenda. \\n";
    }

    @Override
    public String execute(String[] args) {
        return null;
    }

    @Override
    public String description() {
        return "";
    }
}
