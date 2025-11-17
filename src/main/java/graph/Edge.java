package graph;

/**
 * Immutable edge for ConcreteEdgesGraph.
 */
public class Edge {

    private final String source;
    private final String target;
    private final int weight;

    public Edge(String source, String target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    public String getSource() {
        return source;
    }
    public String getTarget() {
        return target;
    }
    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return source + " -> " + target + " (" + weight + ")";
    }
}
