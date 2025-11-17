package graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Set;

import org.junit.Test;

/**
 * Tests for static methods of Graph.
 */
public class GraphStaticTest {

    // Testing strategy:
    // empty() -> should return an empty graph with no vertices
    // No inputs, only output observed via vertices()

    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

    @Test
    public void testEmptyVerticesEmpty() {
        assertEquals("expected empty() graph to have no vertices",
                Collections.emptySet(), Graph.empty().vertices());
    }

    // optional: additional test for new graph functionality
    @Test
    public void testEmptyGraphAddVertex() {
        Graph<String> g = Graph.empty();
        g.add("A");
        assertEquals(Set.of("A"), g.vertices());
    }
}
