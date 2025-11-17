package graph;

import java.util.*;

/**
 * Mutable vertex used in ConcreteVerticesGraph.
 */
public class Vertex<L> {

    private final L label;
    private final Map<L, Integer> sources = new HashMap<>();
    private final Map<L, Integer> targets = new HashMap<>();

    public Vertex(L label) {
        this.label = label;
    }

    public L getLabel() {
        return label;
    }

    public Map<L, Integer> sources() {
        return new HashMap<>(sources);
    }

    public Map<L, Integer> targets() {
        return new HashMap<>(targets);
    }

    public Map<L, Integer> getTargets() {
        return targets;
    }

    public void addSource(L src, int weight) {
        sources.put(src, weight);
    }

    public void addTarget(L tgt, int weight) {
        targets.put(tgt, weight);
    }

    public void removeSource(L src) {
        sources.remove(src);
    }

    public void removeTarget(L tgt) {
        targets.remove(tgt);
    }

    @Override
    public String toString() {
        return label + " targets=" + targets;
    }
}
