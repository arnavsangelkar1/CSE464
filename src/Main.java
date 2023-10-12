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
                String google = "https://www.google.com";
                String wikipedia = "https://www.wikipedia.org";
                String jgrapht =  "http://www.jgrapht.org";

                // Add the initial vertices
                addNode(g, google);
                addNode(g, wikipedia);
                addNode(g, jgrapht);
                //cause the extra vertex error 
                addNode(g, jgrapht);

                // Add edges to create the initial linking structure
                addEdge(g, jgrapht, wikipedia);
                addEdge(g, google, jgrapht);
                addEdge(g, google, wikipedia);
                addEdge(g, wikipedia, google);

                // Export the graph to a DOT file
                exportGraphToDotFile(g, "gh.dot");
        }

        // Method to add a new node (vertex) to the graph

        // Method to add a new node with a label to the graph
        public static void addNode(Graph<URI, DefaultEdge> graph, String label) {
                URI node = URI.create(label);
                if(!graph.containsVertex(node)){
                        graph.addVertex(node);
                }
                else{
                        System.out.println("Node Already Exists " + node);
                }
        }
        // Method to add a list of nodes to the graph
        public static void addNodes(Graph<URI, DefaultEdge> graph, String[] labels) {
                for (String label : labels) {
                        addNode(graph, label);
                }
        }
        // Method to add a new edge to the graph
        // Method to add a new edge with labels to the graph
        public static void addEdge(Graph<URI, DefaultEdge> graph, String srcLabel, String dstLabel) {
                URI src = URI.create(srcLabel);
                URI dst = URI.create(dstLabel);
                graph.addEdge(src, dst);
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
