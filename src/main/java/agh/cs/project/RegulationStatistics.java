package agh.cs.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RegulationStatistics extends Statistics implements ICommand {
    public String execute() {
        return getTop10Regulations();
    }

    public String execute(String[] args) {
        return execute();
    }

    public String getTop10Regulations() {
        StringBuilder bob = new StringBuilder();

        List<ReferencedRegulation> regulationsByReferentions = new ArrayList<ReferencedRegulation>(regulations.values());
        Collections.sort(regulationsByReferentions, new Comparator<ReferencedRegulation>() {
            public int compare(ReferencedRegulation o1, ReferencedRegulation o2) {
                return o2.getNumberOfReferentions() - o1.getNumberOfReferentions(); // Descending.
            }
        });

        for (int i = 0; i < 10; i++) {
            ReferencedRegulation referencedRegulation = regulationsByReferentions.get(i);
            String regulationStat = i + 1 + ": " + referencedRegulation + "\n";
            bob.append(regulationStat);
        }
        return bob.toString();
    }
}
