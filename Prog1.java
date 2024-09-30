/**************************************************************/
/* Benjamin Nguyen                                            */
/* Student ID: 016068702                                      */
/* CS 3310, Fall 2024                                         */
/* Programming Assignment 1                                   */
/* Prog1 class: handles the file reading and program display  */
/**************************************************************/

import java.io.*;
import java.util.Scanner;


public class Prog1
{
    public static void main(String[] args)
    {
        // Take input from the user
        Scanner userInput = new Scanner(System.in);

        // Prompt the user to enter the file name
        System.out.print("Enter the file name: ");
        String fileName = userInput.nextLine();

        try
        {
            // Read in a line from inputed file
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            userInput.close();
            String line = br.readLine();
            int graphNum = 0; // Count for the number of graphs

            while(line != null)
            {
                Scanner in = new Scanner(line);

                // Read the number of vertices
                int numOfVertices = in.nextInt();
                Graph graph = new Graph(numOfVertices + 1);
                graphNum++;

                // Read in Graph edges
                while (in.hasNext())
                {
                    String edge = in.next();

                    // Parse the pair (i,j), removing parentheses and commas
                    Scanner pairScanner = new Scanner(edge);
                    pairScanner.useDelimiter("[(,)]");

                    int i = pairScanner.nextInt();
                    int j = pairScanner.nextInt();
                    graph.addEdge(i, j);

                    pairScanner.close();
                }
                in.close();

                System.out.println("Graph" + graphNum + ":");

                // display the connected components
                graph.findConnectedComponents(1);
                System.out.println("\n");

                line = br.readLine();
            }
            br.close();
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        finally{}
    }
}