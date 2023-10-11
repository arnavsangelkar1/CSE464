import java.net.URI;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultDirectedGraph;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class Main {
        public static void main(String[] args) {
                Graph<URI, DefaultEdge> g = new DefaultDirectedGraph<>(DefaultEdge.class);

                // Create vertex instances
                URI google = URI.create("https://www.google.com");
                URI wikipedia = URI.create("https://www.wikipedia.org");
                URI jgrapht = URI.create("http://www.jgrapht.org");

                // Add the initial vertices
                addVertex(g, google);
                addVertex(g, wikipedia);
                addVertex(g, jgrapht);

                // Add edges to create the initial linking structure
                addEdge(g, jgrapht, wikipedia);
                addEdge(g, google, jgrapht);
                addEdge(g, google, wikipedia);
                addEdge(g, wikipedia, google);

                // Export the graph to a DOT file
                exportGraphToDotFile(g, "test.dot");
        }

        // Method to add a new node (vertex) to the graph
        public static void addVertex(Graph<URI, DefaultEdge> graph, URI vertex) {
                if (!graph.containsVertex(vertex)) {
                        graph.addVertex(vertex);
                }
        }

        // Method to add a new node with a label to the graph
        public static void addNode(Graph<URI, DefaultEdge> graph, String label) {
                URI node = URI.create(label);
                addVertex(graph, node);
        }

        // Method to add a list of nodes to the graph
        public static void addNodes(Graph<URI, DefaultEdge> graph, String[] labels) {
                for (String label : labels) {
                        addNode(graph, label);
                }
        }

        // Method to add a new edge to the graph
        public static void addEdge(Graph<URI, DefaultEdge> graph, URI source, URI target) {
                if (!graph.containsEdge(source, target)) {
                        graph.addEdge(source, target);
                }
        }

        // Method to add a new edge with labels to the graph
        public static void addEdge(Graph<URI, DefaultEdge> graph, String srcLabel, String dstLabel) {
                URI src = URI.create(srcLabel);
                URI dst = URI.create(dstLabel);
                addEdge(graph, src, dst);
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
}
