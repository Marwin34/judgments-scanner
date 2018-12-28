package agh.cs.project.Commands;

import agh.cs.project.Model.ReferencedRegulation;
import agh.cs.project.Model.Statistics;

import java.util.ArrayList;
import java.util.List;

public class RegulationsCommand implements ICommand {

    private Statistics statistics;

    public RegulationsCommand(Statistics statistics) {
        this.statistics = statistics;
    }

    public String execute() {
        return getTop10Regulations();
    }

    public String execute(List<String> args) {
        return "Too many argument! For more information use help." + System.lineSeparator();
    }

    public String getTop10Regulations() {
        StringBuilder bob = new StringBuilder();

        List<ReferencedRegulation> regulationsByReferentions = new ArrayList<>(statistics.getRegulations().values());

        regulationsByReferentions.stream()
                .sorted(((o1, o2) -> o2.getNumberOfReferentions() - o1.getNumberOfReferentions()))
                .limit(10)
                .map(x -> String.format("%-6s %s%n", x.getNumberOfReferentions(), x.toString()))
                .forEach(bob::append);

        return bob.toString();
    }

    @Override
    public String description() {
        return "Display most quoted regulations. regulations.";
    }
}
