package agh.cs.project;

public class JudgesStatistics extends Statistics implements ICommand{

    public String execute() {
        return getTop10Judges();
    }

    public String execute(String[] args) {
        return execute();
    }

    public String getTop10Judges() {
        StringBuilder bob = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            Judge judge = judgesByJudgments.get(i);
            String judgeStats = i + 1 + ": " + judge.getName() + ": " + judge.getNumberOfCases() + "\n";
            bob.append(judgeStats);
        }
        return bob.toString();
    }
}
