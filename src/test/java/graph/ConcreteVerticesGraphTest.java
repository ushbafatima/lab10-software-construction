package graph;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Map;

/**
 * Tests for ConcreteVerticesGraph.
 * Runs GraphInstanceTest tests plus additional tests specific to ConcreteVerticesGraph.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {

    // Provide ConcreteVerticesGraph for abstract tests
    @Override
    public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph();
    }

    /*
     * Testing ConcreteVerticesGraph.toString()
     */
    @Test
    public void testToStringEmpty() {
        ConcreteVerticesGraph g = new ConcreteVerticesGraph();
        assertNotNull(g.toString());
        assertTrue(g.toString().contains("vertices") || g.toString().contains("edges"));
    }

    @Test
    public void testToStringWithVerticesAndEdges() {
        ConcreteVerticesGraph g = new ConcreteVerticesGraph();
        g.add("A");
        g.add("B");
        g.set("A", "B", 4);
        String s = g.toString();
        assertTrue(s.contains("A"));
        assertTrue(s.contains("B"));
    }

    /*
     * Testing Vertex class
     */
    @Test
    public void testVertexConstructorAndGetters() {
        Vertex v = new Vertex("V1");
        assertEquals("V1", v.getLabel());
    }

    @Test
    public void testVertexAddTargetAndSource() {
        Vertex v = new Vertex("V1");
        v.addTarget("V2", 5);
        v.addSource("V0", 3);
        Map<String, Integer> targets = v.targets();
        Map<String, Integer> sources = v.sources();
        assertEquals(5, (int) targets.get("V2"));
        assertEquals(3, (int) sources.get("V0"));
    }

    @Test
    public void testVertexToString() {
        Vertex v = new Vertex("V1");
        v.addTarget("V2", 2);
        String s = v.toString();
        assertTrue(s.contains("V1"));
        assertTrue(s.contains("V2"));
        assertTrue(s.contains("2"));
    }
}
