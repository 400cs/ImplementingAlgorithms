import java.io.*;
import java.util.*;

/**************************************************************/
/* Benjamin Nguyen                                            */
/* Student ID: 016068702                                      */
/* CS 3310, Fall 2024                                         */
/* Programming Assignment 3                                   */
/* CanoeTrip class: compute the optimal costs of traveling    */
/* between any two posts (i, j) where i < j and displays the  */
/*                    */
/**************************************************************/

public class CanoeTrip
{
    public static void main(String[] args) throws IOException
    {
        // check if at least 1 file is provided in commandline
        if (args.length == 0)
        {
            System.out.println("Use: java CanoeTrip filename1 filename2 ...");
            return;
        }
        
        // loop through the files
        for (String fileName: args)
        {
            //Read input file(s)
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            // n = the number of trading posts along the river
            int n = Integer.parseInt(br.readLine().trim());
            
            /* matrix containing costs of traveling between 
               any two posts (i, j) where i < j         */ 
            int[][] costMatrix = new int[n][n];

            // process the input from file - fill cost matrix
            for (int i = 0; i < n - 1; i++)
            {
                String[] costs = br.readLine().trim().split("\\s+");
                for (int j = 0; j < costs.length ; j++)
                {
                    costMatrix[i][i + 1 + j] = Integer.parseInt(costs[j]);
                }
            }
            br.close();

            // print the cost matrix
            System.out.println("Rental Cost Matrix:");
            printFormattedTable(costMatrix);
            System.out.println();

            // initalize optimal Cost matrix
            int[][] optimalCost = new int[n][n];

            // matrix to keep track of intermediate post index b/w posts i & j
            int[][] postRoute = new int[n][n];

            for (int i = 0; i < n - 1; i++)
            {
                for (int j = i + 1; j < n; j++)
                {
                    optimalCost[i][j] = costMatrix[i][j];
                    postRoute[i][j] = i;
                }
            }
            
            /* fill in optimalCost matrix with the minimum cost 
                to travel for each i post to j post */
            /* intialize length = 2 - post 2 is the first post that requires 
                considering intermediate stop(s) with the canoe*/
            for (int length = 2; length < n; length++) // length is j - i
            {
                for (int i = 0; i + length < n; i++) 
                {
                    int j = i + length;
                    for (int k = i + 1; k < j; k++) // Check intermediate stops
                    { 
                        if (optimalCost[i][j] > optimalCost[i][k] + costMatrix[k][j])
                        {
                            optimalCost[i][j] = optimalCost[i][k] + costMatrix[k][j];
                            postRoute[i][j] = k; // Record the intermediate stop
                        }
                    }
                }
            }

            // display the optimal cost matrix 
            System.out.println("Optimal Cost Matrix:");
            printFormattedTable(optimalCost);

            // display the optimal cost
            System.out.println("Optimal Cost for (0, " + (n - 1) +"): " + optimalCost[0][n - 1]);

            // Reconstruct the sequence of rentals for the route from 0 to n-1
            System.out.println("Optimal Route from post 0 to post " + (n - 1) + ":");
            List<Integer> route = new ArrayList<>();
            
            // display the optimal cost route
            reconstructPath(0, n - 1, postRoute, route);
            System.out.println(route);
            System.out.println();
        }

    }

    /*
     * Method: printFormattedTable
     * Purpose: To print the matrix in formatted form
     * Parameters:
     * int[][] matrix - the matrix to print
     * Returns: none
     */
    private static void printFormattedTable(int[][] matrix) 
    {
        int n = matrix.length;
        // Print column headers
        System.out.print("   ");
        for (int j = 0; j < n; j++) 
        {
            System.out.printf("%4d", j);
        }
        System.out.println();

        // Print rows with data
        for (int i = 0; i < n; i++) 
        {
            System.out.printf("%2d ", i); // Row header
            for (int j = 0; j < n; j++) 
            {
                if (i >= j || matrix[i][j] == 0) 
                {
                    System.out.printf("%4s", "-"); // Use "−" for empty paths
                } 
                else 
                {
                    System.out.printf("%4d", matrix[i][j]); // Print the cost
                }
            }
            System.out.println();
        }
    }

    // Helper function to reconstruct the path
    /*
     * Method: reconstructPath
     * Purpose: 
     * Parameters:
     * int i - starting post index of the route
     * int j - ending post index of the route
     * int[][] path - contains the intermediate post index
     *      (if any) between posts i and j for the optimal cost
     * List<Integer> route - contains the sequence of 
     *      rentals to be used for the route
     * Returns: none
     */
    private static void reconstructPath(int i, int j, int[][] path, List<Integer> route) 
    {
        if (i == j) return;

        if (path[i][j] == i) // Direct connection
        {
            route.add(i);
            route.add(j);
        } 
        else 
        {
            reconstructPath(i, path[i][j], path, route);
            route.remove(route.size() - 1); // Avoid duplicate
            reconstructPath(path[i][j], j, path, route);
        }
    }
}