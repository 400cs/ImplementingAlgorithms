/**************************************************************/
/* Benjamin Nguyen                                            */
/* Student ID: 016068702                                      */
/* CS 3310, Fall 2024                                         */
/* Programming Assignment 1                                   */
/* Graph class: information about a single graph              */
/*  uses Depth-First Search to find all of the graph's        */
/*  connected components.                                     */
/**************************************************************/

import java.util.ArrayList;
import java.util.TreeSet;

public class Graph {
    private boolean[] visited;
    private boolean[][] adjacencyMatrix;
    private ArrayList<TreeSet<Integer>> connectedComponents;

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
        this.visited = new boolean[numOfVertices];
        this.adjacencyMatrix 
            = new boolean[numOfVertices][numOfVertices];
        this.connectedComponents 
            = new ArrayList<TreeSet<Integer>>();
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
     * Purpose: find and stores the
     *          graphs' components
     * Parameters:
     * int vertex: the vertex to start 
     * searching for components
     * Returns: none
     */
    public void findConnectedComponents(int vertex)
    {
        for (int i = vertex; i < visited.length; i++)
        {
            if (!visited[i])
            {
                TreeSet<Integer> component = new TreeSet<>();
                dfs(i, component);
                connectedComponents.add(component);
            }
        }
    }
    
    /*
     * Method: dfs
     * Purpose: to traverse the graph
     * Parameters:
     * int vertex: the vertex to visit
     * TreeSet<Integer> component:
     *      ordered set of connected vertices
     * Returns: none
     */
    public void dfs(int vertex, TreeSet<Integer> component)
    {
        visited[vertex] = true;
        component.add(vertex);
        
        for (int j = 1; j < adjacencyMatrix[vertex].length; j++)
        {
            if (adjacencyMatrix[vertex][j] && !visited[j])
            {
                dfs(j, component);
            }
        }
        
    }

    /*
     * Method: displayConnectedComponents
     * Purpose: displays the graph's
     *          connect components
     * Parameters: none
     * Returns: none
     */
    public void displayConnectedComponents()
    {
        if (connectedComponents.size() > 1)
        {
            System.out.print(connectedComponents.size()
                         + " connected components: ");
        }
        else
        {
            System.out.print(connectedComponents.size()
                         + " connected component: ");
        }
        
        for(TreeSet<Integer> component : connectedComponents)
        {
            System.out.print(component + " ");
        }
    }
}