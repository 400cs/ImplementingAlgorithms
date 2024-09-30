/**************************************************************/
/* Benjamin Nguyen                                            */
/* Student ID: 016068702                                      */
/* CS 3310, Fall 2024                                         */
/* Programming Assignment 1                                   */
/* Graph class: information about a single graph              */
/**************************************************************/

import java.util.TreeSet;

public class Graph {
    private int numOfVertices;
    private boolean[] visited;
    private boolean[][] adjacencyMatrix;

    /*
     * Method: Constructor
     * Purpose: constructs the Graph and initalizes
     *          private varibles of graph
     * Parameters:
     * Int numofVertices: the number of vertices in the graph
     * boolean[] visited: the array marking the vertices visited
     * private boolean[][] adjacencyMatrix: a matrix 
     *           representing the graph edges
     * Returns: none
     */
    public Graph(int numOfVertices) 
    {
        this.numOfVertices = numOfVertices;
        this.visited = new boolean[numOfVertices];
        this.adjacencyMatrix 
            = new boolean[numOfVertices][numOfVertices];
    }
    
    /*
     * Method: addEdge
     * Purpose: add an edge between two vertices
     * Parameters:
     * int i, j: any two vertices in the graph
     * Returns: none
     */
    public void addEdge(int i, int j)
    {
        adjacencyMatrix[i][j] = true;
        adjacencyMatrix[j][i] = true;
    }

    /*
     * Method: findConnectedComponents
     * Purpose: find and display
     *          graph's connect components
     * Parameters:
     * int vertex: 
     * Returns: none
     */
    public void findConnectedComponents(int vertex)
    {
        for (int i = vertex; i < visited.length; i++)
        {
            if (!visited[i])
            {
                TreeSet<Integer> components = new TreeSet<>();
                dfs(i, components);
                System.out.print(components + " ");
            }
        }
    }
    
    /*
     * Method: dfs
     * Purpose: to traverse the graph
     * Parameters:
     * int vertex: the vertex to visit
     * TreeSet<Integer> components:
     *      ordered set of connected vertices
     * Returns: none
     */
    public void dfs(int vertex, TreeSet<Integer> components)
    {
        visited[vertex] = true;
        components.add(vertex);
        
        for (int j = 1; j < adjacencyMatrix[vertex].length; j++)
        {
            if (adjacencyMatrix[vertex][j] && !visited[j])
            {
                dfs(j, components);
            }
        }
        
    }
}
