package agh.cs.project.Commands;

import agh.cs.project.Model.ReferencedRegulation;
import agh.cs.project.Model.Statistics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RegulationStatistics implements ICommand {

    private Statistics statistics;

    public RegulationStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public String execute() {
        return getTop10Regulations();
    }

    public String execute(List<String> args) {
        return execute();
    }

    public String getTop10Regulations() {
        StringBuilder bob = new StringBuilder();

        List<ReferencedRegulation> regulationsByReferentions = new ArrayList<>(statistics.getRegulations().values());
        Collections.sort(regulationsByReferentions, (o1, o2) -> {
            return o2.getNumberOfReferentions() - o1.getNumberOfReferentions(); // Descending.
        });

        for (int i = 0; i < 10; i++) {
            ReferencedRegulation referencedRegulation = regulationsByReferentions.get(i);
            String regulationStat = i + 1 + ": " + referencedRegulation + "\n";
            bob.append(regulationStat);
        }
        return bob.toString();
    }

    @Override
    public String description() {
        return "Wystetla cytowane prawa.";
    }
}
