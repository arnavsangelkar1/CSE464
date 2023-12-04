import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomWalkGraphPathFinder extends SearchTemplate {

    private final RandomWalkStrategy randomWalkStrategy;
    private final long seed;

    public RandomWalkGraphPathFinder(RandomWalkStrategy randomWalkStrategy, long seed) {
        this.randomWalkStrategy = randomWalkStrategy;
        this.seed = seed;
    }

    @Override
    protected Path search(Graph<URI, DefaultEdge> graph, URI src, URI dst) {
        Random random = new Random(seed);
        return randomWalkStrategy.randomWalk(graph, src, dst, random);
    }
}

class RandomWalkStrategyImpl implements RandomWalkStrategy {

    @Override
    public Path randomWalk(Graph<URI, DefaultEdge> graph, URI src, URI dst, Random random) {
        Path path = new Path(src);
        URI currentNode = src;

        while (!currentNode.equals(dst)) {
            Set<DefaultEdge> outgoingEdgesSet = graph.outgoingEdgesOf(currentNode);
            if (outgoingEdgesSet.isEmpty()) {
                // No more neighbors to explore
                System.out.println("No more neighbors to explore");
                return null;
            }

            List<DefaultEdge> outgoingEdges = new ArrayList<>(outgoingEdgesSet);
            DefaultEdge randomEdge = outgoingEdges.get(random.nextInt(outgoingEdges.size()));
            URI neighbor = graph.getEdgeTarget(randomEdge);

            path.addNode(neighbor);
            currentNode = neighbor;
            System.out.println("Visiting " + path);
        }

        return path;
    }
}
