package graph;

import static org.junit.Assert.*;
import java.util.Collections;
import java.util.Set;

import org.junit.Test;

/**
 * Tests for static methods of Graph.
 */
public class GraphStaticTest {

    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false;
    }

    @Test
    public void testEmptyVerticesEmpty() {
        assertEquals("expected empty() graph to have no vertices",
                Collections.emptySet(), Graph.empty().vertices());
    }

    @Test
    public void testEmptyGraphAddVertex() {
        Graph<String> g = Graph.empty();
        g.add("A");
        assertEquals(Set.of("A"), g.vertices());
    }
}
