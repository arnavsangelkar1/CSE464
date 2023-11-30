import java.net.URI;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultDirectedGraph;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.HashSet;
import java.util.Set;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import java.util.ArrayList;
import java.net.URI;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultDirectedGraph;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
public class Main {
    public static void main(String[] args) {
        Graph<URI, DefaultEdge> g = new DefaultDirectedGraph<>(DefaultEdge.class);

        // Create vertex instances
        String google = "https://www.google.com";
        String wikipedia = "https://www.wikipedia.org";
        String jgrapht =  "http://www.jgrapht.org";


        // Add the initial vertices
        addNode(g, google);
        addNode(g, wikipedia);
        addNode(g, jgrapht);
        addNode(g, jgrapht);

        // Add edges to create the initial linking structure
        addEdge(g, jgrapht, wikipedia);
        addEdge(g, google, jgrapht);
        addEdge(g, google, wikipedia);
        addEdge(g, wikipedia, google);
        // Export the graph to a DOT file

        exportGraphToDotFile(g, "test.dot");
        removeEdge(g, "https://www.google.com", "http://www.jgrapht.org");


        // Use the graphSearch API to find a path from src to dst
        URI src = URI.create("http://www.jgrapht.org");
        URI dst = URI.create("https://www.google.com");
        Path path = graphSearch(g, src, dst, Algorithm.BFS); // For BFS
        Path path2 = graphSearch(g, src, dst, Algorithm.DFS); // For DFS

        if (path != null) {
            System.out.println("Path found: " + path.toString());
        } else {
            System.out.println("Path not found.");
        }
        if (path2 != null) {
            System.out.println("Path found: " + path2.toString());
        } else {
            System.out.println("Path not found.");
        }
    }

    // Method to add a new node (vertex) to the graph

    // Method to add a new node with a label to the graph
//    public static void addNode(Graph<URI, DefaultEdge> graph, String label) {
//        URI node = URI.create(label);
//        if(!graph.containsVertex(node)){
//            graph.addVertex(node);
//        }
//        else{
//            System.out.println("Node Already Exists " + node);
//        }
//
//    }

   //Refactored method, this way it takes us less space in memory and we do not need to check things twice. It also tells you the node was added
    public static void addNode(Graph<URI, DefaultEdge> graph, String label) {
        URI node = URI.create(label);
        if (graph.addVertex(node)) {
            System.out.println("Node added: " + node);
        } else {
            System.out.println("Node already exists: " + node);
        }
    }

    // Method to remove a nodes to the graph
//    public static void removeNode(Graph<URI, DefaultEdge> graph, String label) {
//        URI node = URI.create(label);
//        if (graph.containsVertex(node)) {
//            graph.removeVertex(node);
//            System.out.println("Node Removed: " + node);
//        } else {
//            System.out.println("Node not found: " + node);
//        }
//    }

    //This refactored method also removes the unnecessary "graph.containsVertex(node)" call, thus making it easier to read
    public static void removeNode(Graph<URI, DefaultEdge> graph, String label) {
        URI node = URI.create(label);
        if (graph.removeVertex(node)) {
            System.out.println("Node Removed: " + node);
        } else {
            System.out.println("Node not found: " + node);
        }
    }

    // Method to add a list of nodes to the graph
    public static void addNodes(Graph<URI, DefaultEdge> graph, String[] labels) {
        for (String label : labels) {
            addNode(graph, label);
        }
    }
    // Method to remove a list of nodes to the graph
    public static void removeNodes(Graph<URI, DefaultEdge> graph, String[] labels) {
        for (String label : labels) {
            removeNode(graph, label);
        }
    }
    // Method to add a new edge to the graph
    // Method to add a new edge with labels to the graph
    //This refactoed branch checks to see if the node already exists in the graph. If it does not it will add it first and then make the edge
    public static void addEdge(Graph<URI, DefaultEdge> graph, String srcLabel, String dstLabel) {
        URI src = URI.create(srcLabel);
        URI dst = URI.create(dstLabel);

        if (!graph.containsVertex(src)) {
            graph.addVertex(src);
        }

        if (!graph.containsVertex(dst)) {
            graph.addVertex(dst);
        }

        graph.addEdge(src, dst);
    }

    // Method to remove an edge from the graph
    public static void removeEdge(Graph<URI, DefaultEdge> graph, String srcLabel, String dstLabel) {
        URI src = URI.create(srcLabel);
        URI dst = URI.create(dstLabel);
        if (graph.containsVertex(src) && graph.containsVertex(dst) && graph.containsEdge(src, dst)) {
            graph.removeEdge(src, dst);
            System.out.println("Edge Removed: " + src + " -> " + dst);
        } else {
            System.out.println("Edge not found: " + src + " -> " + dst);
        }
    }

    // Method to export the graph to a DOT file
    public static void exportGraphToDotFile(Graph<URI, DefaultEdge> graph, String fileName) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("digraph G {");
            for (DefaultEdge edge : graph.edgeSet()) {
                URI source = graph.getEdgeSource(edge);
                URI target = graph.getEdgeTarget(edge);
                printWriter.println("  \"" + source + "\" -> \"" + target + "\";");
            }
            printWriter.println("}");
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Path graphSearch(Graph<URI, DefaultEdge> graph, URI src, URI dst, Algorithm algo) {
        if (algo == Algorithm.BFS) {
            return bfs(graph, src, dst);
        } else if (algo == Algorithm.DFS) {
            return dfs(graph, src, dst);
        } else {
            throw new IllegalArgumentException("Invalid algorithm choice");
        }
    }

    public enum Algorithm {
        BFS, DFS
    }

    private static Path bfs(Graph<URI, DefaultEdge> graph, URI src, URI dst) {
        Queue<Path> queue = new LinkedList<>();
        Set<URI> visited = new HashSet<>();

        queue.add(new Path(src));
        visited.add(src);

        while (!queue.isEmpty()) {
            Path currentPath = queue.poll();
            URI currentNode = currentPath.getLastNode();

            if (currentNode.equals(dst)) {
                return currentPath; // Path found
            }

            for (DefaultEdge edge : graph.outgoingEdgesOf(currentNode)) {
                URI neighbor = graph.getEdgeTarget(edge);
                if (!visited.contains(neighbor)) {
                    Path newPath = new Path(currentPath);
                    newPath.addNode(neighbor);
                    queue.add(newPath);
                    visited.add(neighbor);
                }
            }
        }

        return null; // No path found
    }

    private static Path dfs(Graph<URI, DefaultEdge> graph, URI src, URI dst) {
        java.util.Stack<Path> stack = new java.util.Stack<>();
        Set<URI> visited = new HashSet<>();

        stack.push(new Path(src));
        visited.add(src);

        while (!stack.isEmpty()) {
            Path currentPath = stack.pop();
            URI currentNode = currentPath.getLastNode();

            if (currentNode.equals(dst)) {
                return currentPath; // Path found
            }

            for (DefaultEdge edge : graph.outgoingEdgesOf(currentNode)) {
                URI neighbor = graph.getEdgeTarget(edge);
                if (!visited.contains(neighbor)) {
                    Path newPath = new Path(currentPath);
                    newPath.addNode(neighbor);
                    stack.push(newPath);
                    visited.add(neighbor);
                }
            }
        }

        return null; // No path found
    }


    // Define a Path class to represent a path
    static class Path {
        private List<URI> nodes;

        public Path(URI initialNode) {
            nodes = new ArrayList<>();
            nodes.add(initialNode);
        }

        public Path(Path existingPath) {
            nodes = new ArrayList<>(existingPath.nodes);
        }

        public void addNode(URI node) {
            nodes.add(node);
        }

        public URI getLastNode() {
            return nodes.get(nodes.size() - 1);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < nodes.size(); i++) {
                sb.append(nodes.get(i));
                if (i < nodes.size() - 1) {
                    sb.append(" -> ");
                }
            }
            return sb.toString();
        }
    }
}

