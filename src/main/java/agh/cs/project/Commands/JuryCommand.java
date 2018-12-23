package agh.cs.project.Commands;

import agh.cs.project.Model.Statistics;

import java.util.List;

public class JuryCommand implements ICommand {
    private final Statistics statistics;

    public JuryCommand(Statistics statistics) {
        this.statistics = statistics;
    }

    @Override
    public String execute() {
        return "Specify number of judges.";
    }

    @Override
    public String execute(List<String> args) {
        StringBuilder bob = new StringBuilder();

        for(String judgesNumber : args){
            judgesNumber = judgesNumber.replaceAll("\"", " ").trim();
            if(statistics.getJuryStatistic().containsKey(Integer.parseInt(judgesNumber))){
                bob
                        .append(judgesNumber)
                        .append(": ")
                        .append(statistics.getJuryStatistic().get(Integer.parseInt(judgesNumber)))
                        .append(" judgement(s). \n");
            }else{
                bob
                        .append(judgesNumber)
                        .append(" : 0 judgments. \n");
            }
        }
        return bob.toString();
    }

    @Override
    public String description() {
        return "Display number of cases which have specified number of judges.";
    }
}
