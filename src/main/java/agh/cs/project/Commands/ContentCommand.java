package agh.cs.project.Commands;

import agh.cs.project.Model.DataLoader;

import java.util.List;

public class ContentCommand implements ICommand {

    private final DataLoader loader;

    public ContentCommand(DataLoader loader) {
        this.loader = loader;
    }

    @Override
    public String execute() {
        return null;
    }

    @Override
    public String execute(List<String> args) {
        StringBuilder bob = new StringBuilder();
        for(String signature : args){
            signature = signature.replaceAll("\"", " ").trim();
            if(loader.getJudgments().containsKey(signature)){
                bob.append(loader.getJudgments().get(signature).showJustification())
                        .append(System.lineSeparator());
            }else {
                bob.append("There is no judgment specified by ")
                        .append(signature);
            }

        }
        return bob.toString();
    }

    @Override
    public String description() {
        return "Wyswietla uzasadnienie danego orzeczenia.";
    }
}
