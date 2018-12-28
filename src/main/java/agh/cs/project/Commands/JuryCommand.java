package agh.cs.project.Commands;

import agh.cs.project.Model.Statistics;

import java.util.List;
import java.util.Map;

public class JuryCommand implements ICommand {
    private final Statistics statistics;

    public JuryCommand(Statistics statistics) {
        this.statistics = statistics;
    }

    @Override
    public String execute() {

        StringBuilder bob = new StringBuilder();
        bob.append(String.format("%s%n","Jury statistics:"));


        for(Map.Entry<Integer, Integer> datas : statistics.getJuryStatistic().entrySet()){
            bob.append(String.format("%-3s judge(s) took part in %5s judgment(s).%n", datas.getKey(), datas.getValue()));
        }

        return bob.toString();
    }

    @Override
    public String execute(List<String> args) {
        return "Too many arguments! For more information use help." + System.lineSeparator();
    }

    @Override
    public String description() {
        return "Display number of cases which have specified number of judges. jury.";
    }
}
