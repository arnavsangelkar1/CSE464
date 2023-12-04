import java.util.LinkedList;

public class ConcreteBFS extends BFSAlgo {
    @Override
    protected Iterable<Integer> getNeighbors(int[][] graph, int node) {
        LinkedList<Integer> neighbors = new LinkedList<>();
        for (int i = 0; i < graph.length; i++) {
            if (graph[node][i] == 1) {
                neighbors.add(i);
            }
        }
        return neighbors;
    }

    @Override
    protected void processNode(int node) {
        System.out.println("Custom processing for Node: " + node);
    }
}
