package graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * Abstract tests for instance methods of Graph<String>.
 * Concrete subclasses provide the specific implementation to test.
 */
public abstract class GraphInstanceTest {

    // Abstract method to provide fresh empty instance
    public abstract Graph<String> emptyInstance();

    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with -ea
    }

    @Test
    public void testInitialVerticesEmpty() {
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }

    @Test
    public void testAddVertex() {
        Graph<String> g = emptyInstance();
        assertTrue(g.add("A"));
        assertFalse(g.add("A")); // duplicate
        assertEquals(Set.of("A"), g.vertices());
    }

    @Test
    public void testRemoveVertex() {
        Graph<String> g = emptyInstance();
        g.add("A");
        g.add("B");
        assertTrue(g.remove("A"));
        assertFalse(g.remove("A")); // already removed
        assertEquals(Set.of("B"), g.vertices());
    }

    @Test
    public void testSetEdge() {
        Graph<String> g = emptyInstance();
        g.add("A");
        g.add("B");

        assertEquals(0, g.set("A", "B", 5));
        assertEquals(Map.of("B", 5), g.targets("A"));
        assertEquals(Map.of("A", 5), g.sources("B"));

        // update edge
        assertEquals(5, g.set("A", "B", 7));
        assertEquals(Map.of("B", 7), g.targets("A"));

        // remove edge
        assertEquals(7, g.set("A", "B", 0));
        assertEquals(Collections.emptyMap(), g.targets("A"));
        assertEquals(Collections.emptyMap(), g.sources("B"));
    }

    @Test
    public void testSetEdgeWithNewVertices() {
        Graph<String> g = emptyInstance();
        assertEquals(0, g.set("X", "Y", 10));
        assertEquals(Set.of("X", "Y"), g.vertices());
        assertEquals(Map.of("Y", 10), g.targets("X"));
        assertEquals(Map.of("X", 10), g.sources("Y"));
    }

    @Test
    public void testSourcesAndTargets() {
        Graph<String> g = emptyInstance();
        g.set("A", "B", 3);
        g.set("C", "B", 4);
        g.set("B", "D", 5);

        assertEquals(Map.of("A", 3, "C", 4), g.sources("B"));
        assertEquals(Map.of("D", 5), g.targets("B"));
    }
}
