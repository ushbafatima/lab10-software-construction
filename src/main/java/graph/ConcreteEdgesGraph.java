package graph;

import java.util.*;

/**
 * Graph implementation using a set of Edge objects.
 */
public class ConcreteEdgesGraph implements Graph<String> {

    private final Set<String> vertices = new HashSet<>();
    private final List<Edge> edges = new ArrayList<>();

    @Override
    public boolean add(String vertex) {
        return vertices.add(vertex);
    }

    @Override
    public boolean remove(String vertex) {
        if (!vertices.contains(vertex)) return false;
        vertices.remove(vertex);
        edges.removeIf(e -> e.getSource().equals(vertex) || e.getTarget().equals(vertex));
        return true;
    }

    @Override
    public int set(String source, String target, int weight) {
        vertices.add(source);
        vertices.add(target);

        for (Edge e : edges) {
            if (e.getSource().equals(source) && e.getTarget().equals(target)) {
                int old = e.getWeight();
                edges.remove(e);
                if (weight > 0) edges.add(new Edge(source, target, weight));
                return old;
            }
        }

        if (weight > 0)
            edges.add(new Edge(source, target, weight));

        return 0;
    }

    @Override
    public Set<String> vertices() {
        return new HashSet<>(vertices);
    }

    @Override
    public Map<String, Integer> sources(String target) {
        Map<String, Integer> map = new HashMap<>();
        for (Edge e : edges) {
            if (e.getTarget().equals(target))
                map.put(e.getSource(), e.getWeight());
        }
        return map;
    }

    @Override
    public Map<String, Integer> targets(String source) {
        Map<String, Integer> map = new HashMap<>();
        for (Edge e : edges) {
            if (e.getSource().equals(source))
                map.put(e.getTarget(), e.getWeight());
        }
        return map;
    }

    @Override
    public String toString() {
        return "ConcreteEdgesGraph vertices=" + vertices + " edges=" + edges;
    }
}
