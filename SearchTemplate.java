import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.net.URI;

public abstract class SearchTemplate {

    public void executeSearch(Graph<URI, DefaultEdge> graph, URI src, URI dst, int numTrials) {
        System.out.println("Running search " + numTrials + " times:");

        for (int i = 1; i <= numTrials; i++) {
            System.out.println("Trial " + i + ":");
            Path path = search(graph, src, dst);

            if (path != null) {
                System.out.println("Path found: " + path);
            } else {
                System.out.println("No path found.");
            }

            System.out.println();
        }
    }

    protected abstract Path search(Graph<URI, DefaultEdge> graph, URI src, URI dst);
}
