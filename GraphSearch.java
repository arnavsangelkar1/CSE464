import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.net.URI;

// GraphSearch class using the Strategy pattern
public class GraphSearch {
    private final Algorithm algorithm;

    public GraphSearch(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public void performSearch(DefaultDirectedGraph<URI, DefaultEdge> graph, URI startNode, URI targetNode) {
        algorithm.search(graph, startNode, targetNode);
    }
}
