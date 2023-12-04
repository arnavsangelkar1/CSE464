import java.net.URI;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultDirectedGraph;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.jgrapht.graph.SimpleGraph;
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
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
public class Main {

//    private static String readDotFile(String fileName) throws IOException {
//        Path path = java.nio.file.Paths.get(fileName);  // Use the renamed class
//        return Files.readString(path);
//    }
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
        URI dst = URI.create("A");
        Path path = graphSearch(g, src, dst, Algorithm.BFS); // For BFS
        Path path2 = graphSearch(g, src, dst, Algorithm.DFS); // For DFS

        if (path != null) {
            System.out.println("Path found: " + path.toString());
        } else {
            System.out.println("Path not found: " + src + " -> " + dst);
        }
        if (path2 != null) {
            System.out.println("Path found: " + path2.toString());
        } else {
            System.out.println("Path not found: " + src + " -> " + dst);
        }

        Graph<URI, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

        // Add nodes and edges to the graph using the provided methods
        addNode(graph, "A");
        addNode(graph, "B");
        addNode(graph, "C");
        addNode(graph, "D");
        addNode(graph, "E");
        addNode(graph, "F");

        addEdge(graph, "A", "B");
        addEdge(graph, "B", "C");
        addEdge(graph, "A", "D");
        addEdge(graph, "D", "E");
        addEdge(graph, "E", "F");
        addEdge(graph, "F", "C");

        // Instantiate RandomWalkGraphPathFinder with the RandomWalkStrategyImpl
        RandomWalkGraphPathFinder randomWalkPathFinder = new RandomWalkGraphPathFinder(new RandomWalkStrategyImpl(), System.currentTimeMillis());

        // Specify source and destination vertices
        URI sourceVertex = URI.create("A");
        URI destinationVertex = URI.create("C");

        // Run the search multiple times with different seeds
        
        //Refactored so that it uses a for loop and a seed to take up less memory and be easier to change in the fututre 
        System.out.println("\nRandom Walk for Strat\n");
        for (int i = 1; i <= 1; i++) {
            long seed = System.currentTimeMillis(); // Use a different seed for each trial
            randomWalkPathFinder = new RandomWalkGraphPathFinder(new RandomWalkStrategyImpl(), seed);

            //System.out.println("Running trial " + i + " with seed " + seed + ":");
            randomWalkPathFinder.executeSearch(graph, sourceVertex, destinationVertex, 1);
            System.out.println();
        }
//        exportGraphToDotFile(graph, "test2.dot");
//
//        try {
//            // Read DOT content from the file
//            String dotContent = readDotFile("test.dot");
//
//            int[][] graph3 = DotParser.parseDOT(dotContent);
//            int startNode = 0;
//
//            BFSAlgo bfsAlgorithm = new ConcreteBFS();
//            bfsAlgorithm.bfs(graph3, startNode);
//
//        } catch (IOException e) {
//            System.err.println("Error reading the DOT file: " + e.getMessage());
//        }


        // Create a JGraphT graph with DefaultDirectedGraph
        DefaultDirectedGraph<URI, DefaultEdge> jgraphtGraph = new DefaultDirectedGraph<>(DefaultEdge.class);

        // Specify the path to your DOT file
        String dotFilePath = "test2.dot";

//        DefaultDirectedGraph<URI, DefaultEdge> jgraphtGraph = new DefaultDirectedGraph<>(DefaultEdge.class);
//        String dotFilePath = "path/to/your/file.dot";

        try {
            List<String> lines = Files.readAllLines(Paths.get(dotFilePath));
            for (String line : lines) {
                String[] tokens = line.trim().split("\\s+"); // Split on whitespace

                if (tokens.length > 1 && tokens[1].equals("->")) { // Assumes an edge line
                    try {
                        URI source = createUriFromString(tokens[0]);
                        URI target = createUriFromString(tokens[2]);
                        jgraphtGraph.addVertex(source);
                        jgraphtGraph.addVertex(target);
                        jgraphtGraph.addEdge(source, target);
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("JGraphT Graph: " + jgraphtGraph);

        //Path path3 = graphSearch(jgraphtGraph, sourceVertex, destinationVertex, Algorithm.BFS); // For BFS

//        if (path3 != null) {
//            System.out.println("Path found: " + path3.toString());
//        } else {
//            System.out.println("Path not found.");
//        }

//        ConcreteBFS concreteBFS = new ConcreteBFS(jgraphtGraph);
//        //URI startNode = sourceVertex;
//        concreteBFS.search(jgraphtGraph, sourceVertex, destinationVertex);
//
//        DepthFirstSearchTemplate dfsTemplate = new CustomDFS();
//        dfsTemplate.search(jgraphtGraph, sourceVertex, destinationVertex);
//
//
        BFSAlgo bfsAlgorithm = new BFSAlgo();
        DepthFirstSearchTemplate dfsAlgorithm = new DepthFirstSearchTemplate();

        // Use GraphSearch with different algorithms
        System.out.println("\nBFS for Strat\n");
        GraphSearch bfsSearch = new GraphSearch(bfsAlgorithm);
        bfsSearch.performSearch(jgraphtGraph, sourceVertex, destinationVertex);

        System.out.println("\nDFS for Strat");
        GraphSearch dfsSearch = new GraphSearch(dfsAlgorithm);
        dfsSearch.performSearch(jgraphtGraph, sourceVertex, destinationVertex);

        //RandomWalkSearch randomWalkSearch = new RandomWalkSearch();
//        List<Path> resultPaths = randomWalkSearch.search(jgraphtGraph, sourceVertex, destinationVertex);
//
//        // Print the result paths
//        for (Path path : resultPaths) {
//            System.out.println("visiting " + path);
//        }
    }


    private static URI createUriFromString(String uriString) throws URISyntaxException {
        // Add a scheme (e.g., "http") if the URI is missing one

        // Replace illegal characters in the scheme
        uriString = uriString.replaceAll("[^a-zA-Z0-9+.-]+", "");

        return new URI(uriString);
    }
//    public class Main {
//        public static void main(String[] args) {
//            // Create a graph
//            Graph<URI, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
//
//            // Add nodes and edges to the graph using the provided methods
//            addNode(graph, "A");
//            addNode(graph, "B");
//            addNode(graph, "C");
//            addNode(graph, "D");
//            addNode(graph, "E");
//            addNode(graph, "F");
//
//            addEdge(graph, "A", "B");
//            addEdge(graph, "B", "C");
//            addEdge(graph, "A", "D");
//            addEdge(graph, "D", "E");
//            addEdge(graph, "E", "F");
//            addEdge(graph, "F", "C");
//
//            // Instantiate RandomWalkGraphPathFinder with the RandomWalkStrategyImpl
//            RandomWalkGraphPathFinder randomWalkPathFinder = new RandomWalkGraphPathFinder(new RandomWalkStrategyImpl(), System.currentTimeMillis());
//
//            // Specify source and destination vertices
//            URI sourceVertex = URI.create("A");
//            URI destinationVertex = URI.create("C");
//
//            // Run the search multiple times with different seeds
//            for (int i = 1; i <= 3; i++) {
//                long seed = System.currentTimeMillis(); // Use a different seed for each trial
//                randomWalkPathFinder = new RandomWalkGraphPathFinder(new RandomWalkStrategyImpl(), seed);
//
//                System.out.println("Running trial " + i + " with seed " + seed + ":");
//                randomWalkPathFinder.executeSearch(graph, sourceVertex, destinationVertex, 1);
//                System.out.println();
//            }
//        }
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
//    public static void removeEdge(Graph<URI, DefaultEdge> graph, String srcLabel, String dstLabel) {
//        URI src = URI.create(srcLabel);
//        URI dst = URI.create(dstLabel);
//        if (graph.containsVertex(src) && graph.containsVertex(dst) && graph.containsEdge(src, dst)) {
//            graph.removeEdge(src, dst);
//            System.out.println("Edge Removed: " + src + " -> " + dst);
//        } else {
//            System.out.println("Edge not found: " + src + " -> " + dst);
//        }
//    }
    //This refactored branch looks at removing unncessary calls of graph.containsVertex

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
    //This new refactored branch of path makes switch statments instead of if statments. This helps with readability as well as error checking with the program.
    public static Path graphSearch(Graph<URI, DefaultEdge> graph, URI src, URI dst, Algorithm algo) {
        switch (algo) {
            case BFS:
                return bfs(graph, src, dst);
            case DFS:
                return dfs(graph, src, dst);
            default:
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
