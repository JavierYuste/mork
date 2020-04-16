package es.urjc.etsii.grafo.io;

import es.urjc.etsii.grafo.solution.Solution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The result of the execution of an algorithm
 * Contains all the generated solutions and some stats about them
 */
public class WorkingOnResult {

    private final ArrayList<Solution> solutions;
    private Instance instance;
    private final String algorythmName;
    private final String instanceName;

    /**
     * Initialize a result class.
     *
     * @param nSolutions   estimated number of solutions we are generating
     * @param algName      Algorithm's name
     * @param instanceName Instance name
     */
    public WorkingOnResult(int nSolutions, String algName, String instanceName) {
        this.solutions = new ArrayList<>(nSolutions);
        this.algorythmName = algName;
        this.instanceName = instanceName;
    }

    /**
     * Store new result
     *
     * @param s     Calculated es.urjc.etsii.grafo.solution
     * @param nanos Time used to calculate the given es.urjc.etsii.grafo.solution
     */
    public void addSolution(Solution s, long nanos) {
        s.setExecutionTimeInNanos(nanos);
        this.solutions.add(s);
        if (instance == null) {
            instance = s.getInstance();
        } else if (instance != s.getInstance()) {
            throw new AssertionError(String.format("Instance mismatch, expected %s got %s", instance.getName(), s.getInstance().getName()));
        }
    }

    public long getAverageExecTime() {
        long totalTime = 0;
        for (var solution : this.solutions) {
            totalTime += solution.getExecutionTimeInNanos();
        }
        return totalTime / this.solutions.size() / 1_000_000; // 1 millisecond = 10^6 nanos
    }

    public String getFormattedAverageFO(int nDecimales) {
        String formatString = "%." + nDecimales + "f";
        return String.format(formatString, this.getAverageFOValue());
    }

    public double getAverageFOValue() {
        double value = 0;
        for (var solution : solutions) {
            value += solution.getOptimalValue();
        }
        return value / solutions.size();
    }

    public long getTotalTime() {
        long totalTime = 0;
        for (var solution : solutions) {
            totalTime += solution.getExecutionTimeInNanos();
        }
        return totalTime / 1_000_000;
    }

    /**
     * Returns the standard deviation for the objective values of all solutions
     *
     * @return The STD
     */
    public double getStd() {
        double total = 0;
        double avg = getAverageFOValue();
        for (Solution solution : this.solutions) {
            // La varianza es la suma de las diferencias al cuadrado
            // dividido entre el tamaño del conjunto menos 1
            // La desviacion estandar es sqrt(varianza)
            double difference = solution.getOptimalValue() - avg;
            total += difference * difference;
        }

        return Math.sqrt(total / (this.solutions.size() - 1));
    }

    public Solution getBestSolution() {
        Solution chosen = null;
        for (Solution solution : solutions) {
            if(chosen == null)  chosen = solution;
            else chosen = chosen.getBetterSolution(solution);
        }
        return chosen;
    }

    public List<Solution> getSolutions() {
        return Collections.unmodifiableList(this.solutions);
    }

    public String getAlgorythmName() {
        return this.algorythmName;
    }

    public String getInstanceName() {
        return instanceName;
    }

    // TODO review result to string
    public String toString() {
        return "Instance Name: " + this.instanceName
                + "\nAlgorythm Used: " + this.algorythmName
                + "\nAverage Obj.Function: " + getFormattedAverageFO(2)
                + "\nExecution Time (ms): " + this.getAverageExecTime()
                + "\n---------------------------------------------------------";
    }

    public Result finish() {
        return new Result(
                this.algorythmName,
                this.instanceName,
                Double.toString(this.getAverageFOValue()),
                Double.toString(this.getBestSolution().getOptimalValue()),
                Double.toString(this.getStd()),
                Double.toString(this.getAverageExecTime()),
                Long.toString(this.getTotalTime())
        );
    }
}