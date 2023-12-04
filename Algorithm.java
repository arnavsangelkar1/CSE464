// Algorithm.java
import java.net.URI;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultDirectedGraph;

// Algorithm interface
public interface Algorithm {
    void search(DefaultDirectedGraph<URI, DefaultEdge> graph, URI startNode, URI targetNode);
}

