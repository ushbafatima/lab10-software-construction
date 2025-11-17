package graph;

import java.util.*;

/**
 * Graph implementation using a list of Vertex objects.
 */
public class ConcreteVerticesGraph<L> implements Graph<L> {

    private final List<Vertex<L>> vertices = new ArrayList<>();

    private Vertex<L> find(L label) {
        for (Vertex<L> v : vertices) {
            if (v.getLabel().equals(label))
                return v;
        }
        return null;
    }

    @Override
    public boolean add(L vertex) {
        if (find(vertex) != null) return false;
        vertices.add(new Vertex<>(vertex));
        return true;
    }

    @Override
    public boolean remove(L vertex) {
        Vertex<L> v = find(vertex);
        if (v == null) return false;

        // remove edges from all vertices
        for (Vertex<L> other : vertices) {
            other.removeTarget(vertex);
            other.removeSource(vertex);
        }

        return vertices.remove(v);
    }

    @Override
    public int set(L source, L target, int weight) {
        if (find(source) == null) add(source);
        if (find(target) == null) add(target);

        Vertex<L> s = find(source);
        Vertex<L> t = find(target);

        int prev = s.getTargets().getOrDefault(target, 0);

        if (weight == 0) {
            s.removeTarget(target);
            t.removeSource(source);
        } else {
            s.addTarget(target, weight);
            t.addSource(source, weight);
        }

        return prev;
    }

    @Override
    public Set<L> vertices() {
        Set<L> result = new HashSet<>();
        for (Vertex<L> v : vertices) result.add(v.getLabel());
        return result;
    }

    @Override
    public Map<L, Integer> sources(L target) {
        Map<L, Integer> result = new HashMap<>();
        for (Vertex<L> v : vertices) {
            if (v.getTargets().containsKey(target))
                result.put(v.getLabel(), v.getTargets().get(target));
        }
        return result;
    }

    @Override
    public Map<L, Integer> targets(L source) {
        Vertex<L> v = find(source);
        if (v == null) return Map.of();
        return new HashMap<>(v.getTargets());
    }

    @Override
    public String toString() {
        return "ConcreteVerticesGraph with vertices=" + vertices();
    }
}
