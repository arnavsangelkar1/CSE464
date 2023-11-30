import java.net.URI;

public class Node {

    private final URI value;

    public Node(URI value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                value +
                '}';
    }
}