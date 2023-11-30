import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class Path {

    private final List<Node> nodes;

    public Path(URI initialNode) {
        this.nodes = new ArrayList<>();
        this.nodes.add(new Node(initialNode));
    }

    public void addNode(URI node) {
        this.nodes.add(new Node(node));
    }

    @Override
    public String toString() {
        return "Path{" +
                "nodes=" + nodes +
                '}';
    }
}
