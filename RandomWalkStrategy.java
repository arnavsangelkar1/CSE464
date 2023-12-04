import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.net.URI;
import java.util.Random;

public interface RandomWalkStrategy {
    Path randomWalk(Graph<URI, DefaultEdge> graph, URI src, URI dst, Random random);
}
