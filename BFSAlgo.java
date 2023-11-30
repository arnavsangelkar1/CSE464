import java.util.Queue;
import java.util.LinkedList;

public abstract class BFSAlgo {
    // Template method
    public final void bfs(int[][] graph, int startNode) {
        //System.out.println("Hello ");
        boolean[] visited = new boolean[graph.length];
        Queue<Integer> queue = new LinkedList<>();

        // Mark the start node as visited and enqueue it
        visited[startNode] = true;
        queue.add(startNode);

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            processNode(currentNode);

            for (int neighbor : getNeighbors(graph, currentNode)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }

    // Abstract method to be implemented by subclasses
    protected abstract Iterable<Integer> getNeighbors(int[][] graph, int node);

    // Hook method that can be overridden by subclasses
    protected void processNode(int node) {
        System.out.println("Visiting Node: " + node);
    }
}
