import java.io.BufferedReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class DotParser {
    public static int[][] parseDOT(String dotContent) {
        Map<String, Integer> nodeIndices = new HashMap<>();
        int nodeCount = 0;

        try (BufferedReader reader = new BufferedReader(new StringReader(dotContent))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("->")) {
                    String[] nodes = line.split("->");
                    String source = nodes[0].trim().replace("\"", "");
                    String target = nodes[1].trim().replace("\"", "");

                    nodeIndices.putIfAbsent(source, nodeCount++);
                    nodeIndices.putIfAbsent(target, nodeCount++);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        int[][] graph = new int[nodeCount][nodeCount];

        try (BufferedReader reader = new BufferedReader(new StringReader(dotContent))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("->")) {
                    String[] nodes = line.split("->");
                    String source = nodes[0].trim().replace("\"", "");
                    String target = nodes[1].trim().replace("\"", "");

                    int sourceIndex = nodeIndices.get(source);
                    int targetIndex = nodeIndices.get(target);

                    graph[sourceIndex][targetIndex] = 1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return graph;
    }
}
