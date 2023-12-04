import java.net.URI;

// Concrete subclass implementing additional processing
public class CustomDFS extends DepthFirstSearchTemplate {
    @Override
    protected void additionalProcessing(URI currentNode) {
        // Additional processing specific to this subclass
        //System.out.print("(Custom Processing: " + currentNode + ") ");
    }
}
