# ProjectPart1
This code will create a graph as well as add odes and edges. 
The code will however download the graph into a dot file and can be made into a png with comand line promnt. 
All of the test cases are in the main file. 


What the end project will look like as a png after the command line prompt (dot -Tpng input.dot -o output.png) 
![gh](https://github.com/arnavsangelkar1/ProjectPart1/assets/147441660/1d1d1d84-0f08-486c-b259-4791723fa50e)

My Commits: 
![42FC6AF5-D7C3-4972-B76A-5843CDE1AE6F](https://github.com/arnavsangelkar1/CSE464/assets/147441660/7b51a97d-6b12-406d-9f70-47f09c852157)

Output: It will say that "This node Already Exists *Insert Node*" When there are duplicates as well as with edges. It will output a dot file into the file and then you can use command line to make it a png. 

Here are expected outputs for removing nodes, edges as well as the updated path. The code has algorithim to look at BFS and DFS depending on what the user wants. 

![Image 11-5-23 at 7 03 PM](https://github.com/arnavsangelkar1/CSE464/assets/147441660/0d2cad35-c664-4bd0-8e63-90838f650ff3)
![Image 11-5-23 at 7 06 PM](https://github.com/arnavsangelkar1/CSE464/assets/147441660/25d1dddb-6ff9-4738-a4e2-472f367ce0e2)
![Image 11-5-23 at 7 09 PM](https://github.com/arnavsangelkar1/CSE464/assets/147441660/5e6fee63-7d1d-46d5-b8d6-2a11e7a8f11b)

Here are my commits for this section the merge branch is also in there: 

![Image 11-5-23 at 7 13 PM](https://github.com/arnavsangelkar1/CSE464/assets/147441660/4c51f111-9fa0-4dce-a577-9bd1594481ec)

Here is showing that my link is in the google sheet:
![Image 11-5-23 at 7 14 PM](https://github.com/arnavsangelkar1/CSE464/assets/147441660/fec17d37-241a-4d48-b4ff-4f17ea667384)

Here is the continous support: 
![Image 11-5-23 at 7 11 PM](https://github.com/arnavsangelkar1/CSE464/assets/147441660/5b2c4193-613c-474c-b847-007ff4fd75f2)


Course Project Part3: 

Here are my 5 refactorings: 

For reafactor 1: Refactored so that it uses a for loop and a seed to take up less memory and be easier to change in the fututre
For reafactor 2: Refactored method, this way it takes us less space in memory and we do not need to check things twice. It also tells you the node was added
For reafactor 3: This refactored method also removes the unnecessary "graph.containsVertex(node)" call, thus making it easier to read and change in the furure 
For reafactor 4: This refactoed branch checks to see if the node already exists in the graph. If it does not it will add it first and then make the edge
For reafactor 5: This refactored branch looks at removing unncessary calls of graph.containsVertex making it take up less space in memory and easier to read 

Here is all of my comits for this part of the project: 

![Image 12-3-23 at 7 29 PM](https://github.com/arnavsangelkar1/CSE464/assets/147441660/42b7479f-8304-4b38-a84c-9135f6eb58db)

When looking at the Template Method design pattern is utilized to structure the graph search algorithms BFS and DFS. An abstract class and interface makes the template method) outlining the algorithm's high-level structure. Subclasses, representing specific algorithms, extend or implement this template, providing concrete implementations for algorithm-specific steps. We used these both of BFS and DFS. We have the interface as well to show the Stragagy Template as well. We can see the diffrences in BFS and DFS in that there are diffrent classes used to call each of them and thier own subclasses. 

We have the Algorithim Interface to implement the stratagy pattern. We use this call void search(DefaultDirectedGraph<URI, DefaultEdge> graph, URI startNode, URI targetNode);
} to pick what we want. And then each class implements this code. 

Here is the output for the Random Walk: 

![Image 12-3-23 at 7 38 PM](https://github.com/arnavsangelkar1/CSE464/assets/147441660/3ee89afc-b3f6-479e-90b3-387e6f3bbf9e)




