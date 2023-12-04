import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.net.URI;
import java.util.Stack;

// Abstract class defining the DFS template
public abstract class DepthFirstSearchTemplate {

    // Template method that encapsulates the DFS algorithm
    public final void dfs(DefaultDirectedGraph<URI, DefaultEdge> graph, URI startNode) {
        boolean[] visited = new boolean[graph.vertexSet().size()];
        Stack<URI> stack = new Stack<>();
        StringBuilder path = new StringBuilder();

        // Push the initial node onto the stack
        stack.push(startNode);

        while (!stack.isEmpty()) {
            URI currentNode = stack.pop();
            int currentIndex = getIndex(graph, currentNode);

            // Process the current node
            if (!visited[currentIndex]) {
                System.out.print(currentNode + " ");
                visited[currentIndex] = true;

                // Append to the path
                path.append(currentNode).append(" -> ");
            }

            // Get neighbors and push them onto the stack if not visited
            graph.outgoingEdgesOf(currentNode).forEach(edge -> {
                URI neighbor = graph.getEdgeTarget(edge);
                int neighborIndex = getIndex(graph, neighbor);
                if (!visited[neighborIndex]) {
                    stack.push(neighbor);
                }
            });

            // Hook method for subclasses to override if needed
            additionalProcessing(currentNode);
        }

        // Print the path
        System.out.println("\nPath: " + path.substring(0, path.length() - 4)); // Remove the last " -> "
    }

    private int getIndex(DefaultDirectedGraph<URI, DefaultEdge> graph, URI node) {
        int index = 0;
        for (URI vertex : graph.vertexSet()) {
            if (vertex.equals(node)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    // Hook method for subclasses to override additional processing
    protected void additionalProcessing(URI currentNode) {
        // Default implementation does nothing
    }
}
