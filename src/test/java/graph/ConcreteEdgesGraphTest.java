package graph;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Map;

/**
 * Tests for ConcreteEdgesGraph.
 * Runs GraphInstanceTest tests plus additional tests specific to ConcreteEdgesGraph.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {

    // Provide ConcreteEdgesGraph for abstract tests
    @Override
    public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph();
    }

    /*
     * Testing ConcreteEdgesGraph.toString()
     */
    @Test
    public void testToStringEmpty() {
        ConcreteEdgesGraph g = new ConcreteEdgesGraph();
        assertNotNull(g.toString());
        assertTrue(g.toString().contains("vertices") || g.toString().contains("edges"));
    }

    @Test
    public void testToStringWithEdges() {
        ConcreteEdgesGraph g = new ConcreteEdgesGraph();
        g.set("A", "B", 5);
        g.set("B", "C", 3);
        String s = g.toString();
        assertTrue(s.contains("A"));
        assertTrue(s.contains("B"));
        assertTrue(s.contains("C"));
    }

    /*
     * Testing Edge class
     */
    @Test
    public void testEdgeConstructorAndGetters() {
        Edge e = new Edge("X", "Y", 10);
        assertEquals("X", e.getSource());
        assertEquals("Y", e.getTarget());
        assertEquals(10, e.getWeight());
    }

    @Test
    public void testEdgeToString() {
        Edge e = new Edge("X", "Y", 10);
        String s = e.toString();
        assertTrue(s.contains("X"));
        assertTrue(s.contains("Y"));
        assertTrue(s.contains("10"));
    }
}
