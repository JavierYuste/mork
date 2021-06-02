package es.urjc.etsii.grafo.solver.destructor;


import es.urjc.etsii.grafo.io.Instance;
import es.urjc.etsii.grafo.solution.Solution;

/**
 * Different ways to shake a solution, RandomShake for a reference implementation
 * @param <S> Solution class
 * @param <I> Instance class
 * @see RandomMoveShake
 */
public abstract class Shake<S extends Solution<I>, I extends Instance> {

    /**
     * Shake the solution. Use currentK and maxK to calculate how powerful the shake should be in your implementation.
     * @param s Solution to shake
     * @param currentK Current shake strength
     * @param maxK Max strength
     * @param inPlace true to execute shake over the same solution, false to clone first and work in a copy
     * @return shook solution
     */
    public abstract S shake(S s, int currentK, int maxK, boolean inPlace);
}