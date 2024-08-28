package es.urjc.etsii.grafo.config;

import es.urjc.etsii.grafo.util.random.RandomType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration file based on application.yml file.
 * {@see application.yml}
 */
@Configuration
@ConfigurationProperties(prefix = "solver")
public class SolverConfig {

    /**
     * Global random seed to ensure reproducibility
     */
    private long seed = 1234;

    /**
     * Random generator to use
     */
    private RandomType randomType;

    /**
     * Experiment names
     */
    private String experiments;

    /**
     * How many times should each experiment be repeated.
     */
    private int repetitions = 1;

    /**
     * Chooses the executo for all experiments. True to use the ConcurrentExecutor, false to use sequential executor.
     * @see es.urjc.etsii.grafo.executors.SequentialExecutor
     * @see es.urjc.etsii.grafo.executors.ConcurrentExecutor
     */
    private boolean parallelExecutor = false;

    /**
     * Number of workers to use if parallelExecutor is enabled
     */
    private int nWorkers = -1;

    /**
     * Execute benchmark before starting solver
     */
    private boolean benchmark = false;

    /**
     * Tree depth when using automatic configuration
     */
    private int treeDepth = 4;

    private int maxDerivationRepetition = 2;

    /**
     * For each solution generated by any algorithm, ignore this millis in the area calculation.
     * WARNING: Any algorithm that does not report an o.f value before this limit is reached is considered invalid
     */
    private long ignoreInitialMillis = 10_000;

    /**
     * Area will be measured in interval [ignoreInitialMillis, ignoreInitialMillis+intervalDurationMillis]
     */
    private long intervalDurationMillis = 50_000;

    /**
     * Determine irace execution budget dynamically depending on the number of parameters to tune.
     * Used only when autoconfig is enabled, for each 50 params uses 10k executions
     */
    private int experimentsPerParameter = 200; // 50 params --> 10k\

    /**
     * Integration key for the execution controller when running in follower mode.
     */
    private String integrationKey;

    /**
     * Scale area under curve when using autoconfig procedure using natural logarithm
     */
    private boolean logScaleArea = true;

    /**
     * Minimum number of tuning experiments to run, even if there are few parameters.
     */
    private int minimumNumberOfExperiments = 10000;

    /**
     * Restart algorithms during autoconfig tuning if they finish before the allocated computational budget is consumed.
     * Otherwise, if the algorithm finishes before the budget is consumed, the remaining time is wasted,
     * and faster algorithms may be considered worse.
     */
    private boolean autorestart = true;

    /**
     * Metrics tracking
     */
    private boolean metrics = false;


    /**
     * <p>Getter for the field <code>seed</code>.</p>
     *
     * @return a int.
     */
    public long getSeed() {
        return seed;
    }

    /**
     * <p>Setter for the field <code>seed</code>.</p>
     *
     * @param seed a int.
     */
    public void setSeed(long seed) {
        this.seed = seed;
    }

    /**
     * <p>Getter for the field <code>experiments</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getExperiments() {
        return experiments;
    }

    /**
     * <p>Setter for the field <code>experiments</code>.</p>
     *
     * @param experiments a {@link java.lang.String} object.
     */
    public void setExperiments(String experiments) {
        this.experiments = experiments;
    }

    /**
     * <p>Getter for the field <code>repetitions</code>.</p>
     *
     * @return a int.
     */
    public int getRepetitions() {
        return repetitions;
    }

    /**
     * <p>Setter for the field <code>repetitions</code>.</p>
     *
     * @param repetitions a int.
     */
    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    /**
     * <p>isParallelExecutor.</p>
     *
     * @return a boolean.
     */
    public boolean isParallelExecutor() {
        return parallelExecutor;
    }

    /**
     * <p>Setter for the field <code>parallelExecutor</code>.</p>
     *
     * @param parallelExecutor a boolean.
     */
    public void setParallelExecutor(boolean parallelExecutor) {
        this.parallelExecutor = parallelExecutor;
    }

    /**
     * <p>Getter for the field <code>nWorkers</code>.</p>
     * If nWorkers was set to 0 or a negative value, returns availableProcessors() / 2
     * @return a int.
     */
    public int getnWorkers() {
        if (nWorkers < 1) {
            return Runtime.getRuntime().availableProcessors() / 2;
        }
        return nWorkers;
    }

    /**
     * <p>Setter for the field <code>nWorkers</code>.</p>
     *
     * @param nWorkers a int.
     */
    public void setnWorkers(int nWorkers) {
        this.nWorkers = nWorkers;
    }

    /**
     * <p>isBenchmark.</p>
     *
     * @return a boolean.
     */
    public boolean isBenchmark() {
        return benchmark;
    }

    /**
     * <p>Setter for the field <code>benchmark</code>.</p>
     *
     * @param benchmark a boolean.
     */
    public void setBenchmark(boolean benchmark) {
        this.benchmark = benchmark;
    }

    /**
     * <p>Getter for the field <code>randomType</code>.</p>
     *
     * @return a {@link es.urjc.etsii.grafo.util.random.RandomType} object.
     */
    public RandomType getRandomType() {
        return randomType;
    }

    /**
     * <p>Setter for the field <code>randomType</code>.</p>
     *
     * @param randomType a {@link es.urjc.etsii.grafo.util.random.RandomType} object.
     */
    public void setRandomType(RandomType randomType) {
        this.randomType = randomType;
    }

    /**
     * Get autoconfig tree depth
     * @return autoconfig tree depth
     */
    public int getTreeDepth() {
        return treeDepth;
    }

    /**
     * Set autoconfig tree depth
     * @param treeDepth autoconfig tree depth
     */
    public void setTreeDepth(int treeDepth) {
        this.treeDepth = treeDepth;
    }

    public boolean isMetrics() {
        return metrics;
    }

    public void setMetrics(boolean metrics) {
        this.metrics = metrics;
    }

    public long getIgnoreInitialMillis() {
        return ignoreInitialMillis;
    }

    public void setIgnoreInitialMillis(long ignoreInitialMillis) {
        this.ignoreInitialMillis = ignoreInitialMillis;
    }

    public long getIntervalDurationMillis() {
        return intervalDurationMillis;
    }

    public void setIntervalDurationMillis(long intervalDurationMillis) {
        this.intervalDurationMillis = intervalDurationMillis;
    }

    public int getExperimentsPerParameter() {
        return experimentsPerParameter;
    }
    public void setExperimentsPerParameter(int experimentsPerParameter) {
        this.experimentsPerParameter = experimentsPerParameter;
    }

    public int getMinimumNumberOfExperiments() {
        return minimumNumberOfExperiments;
    }

    public void setMinimumNumberOfExperiments(int minimumNumberOfExperiments) {
        this.minimumNumberOfExperiments = minimumNumberOfExperiments;
    }

    public boolean isAutorestart() {
        return autorestart;
    }

    public void setAutorestart(boolean autorestart) {
        this.autorestart = autorestart;
    }

    public int getMaxDerivationRepetition() {
        return maxDerivationRepetition;
    }

    public void setMaxDerivationRepetition(int maxDerivationRepetition) {
        this.maxDerivationRepetition = maxDerivationRepetition;
    }

    /**
     * Scale area under curve using natural logarithm
     * @return true if area should be scaled, false to keep value as is
     */
    public boolean isLogScaleArea() {
        return logScaleArea;
    }

    /**
     * Scale area under curve using natural logarithm
     * @param logScaleArea true if area should be scaled, false to keep value as is
     */
    public void setLogScaleArea(boolean logScaleArea) {
        this.logScaleArea = logScaleArea;
    }

    /**
     * Integration key for the execution controller when running in follower mode.
     * @return integration key as a String
     */
    public String getIntegrationKey() {
        return integrationKey;
    }

    /**
     * Integration key for the execution controller when running in follower mode.
     * @param integrationKey integration key as a String
     */
    public void setIntegrationKey(String integrationKey) {
        this.integrationKey = integrationKey;
    }
}
