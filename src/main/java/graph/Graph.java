package graph;

import java.util.Map;
import java.util.Set;

/**
 * A mutable directed weighted graph with String vertices.
 */
public interface Graph<L> {

    /**
     * Create and return a new empty graph.
     */
    public static <L> Graph<L> empty() {
        return new ConcreteVerticesGraph<>();
    }

    /**
     * Add a vertex to the graph.
     * @param vertex label for the new vertex
     * @return true if vertex added, false if already present
     */
    public boolean add(L vertex);

    /**
     * Remove a vertex and all incident edges.
     * @param vertex
     * @return true if removed, false if not present
     */
    public boolean remove(L vertex);

    /**
     * Set the weight of the edge (source → target).
     * If weight = 0, the edge is removed.
     * @return previous weight, or 0 if none
     */
    public int set(L source, L target, int weight);

    /**
     * @return all vertices in graph
     */
    public Set<L> vertices();

    /**
     * @return map of sources pointing to target
     */
    public Map<L, Integer> sources(L target);

    /**
     * @return map of targets reachable from source
     */
    public Map<L, Integer> targets(L source);
}
