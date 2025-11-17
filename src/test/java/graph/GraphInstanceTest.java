package graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * Tests for instance methods of Graph<String>.
 *
 * Testing strategy:
 * - add(vertex)
 *   - add a new vertex to empty graph
 *   - add a duplicate vertex
 * - remove(vertex)
 *   - remove existing vertex
 *   - remove non-existing vertex
 * - set(source, target, weight)
 *   - add new edge between existing vertices
 *   - add edge where vertices don't exist yet (vertices added automatically)
 *   - update weight of existing edge
 *   - remove edge (weight 0)
 * - vertices()
 *   - check vertices after add/remove
 * - sources(target)
 *   - check correct sources map
 * - targets(source)
 *   - check correct targets map
 */
public abstract class GraphInstanceTest {

    /**
     * Overridden by implementation-specific test classes.
     *
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();

    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
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

        // add new edge
        assertEquals(0, g.set("A", "B", 5));
        assertEquals(Map.of("B", 5), g.targets("A"));
        assertEquals(Map.of("A", 5), g.sources("B"));

        // update edge weight
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

        // vertices "X" and "Y" don't exist yet
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

        Map<String, Integer> sourcesB = g.sources("B");
        assertEquals(Map.of("A", 3, "C", 4), sourcesB);

        Map<String, Integer> targetsB = g.targets("B");
        assertEquals(Map.of("D", 5), targetsB);
    }
}
