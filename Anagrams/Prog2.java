package Anagrams;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


/**************************************************************/
/* Benjamin Nguyen                                            */
/* Student ID: 016068702                                      */
/* CS 3310, Fall 2024                                         */
/* Programming Assignment 2                                   */
/* Prog2 class: handles the file reading and program display  */
/**************************************************************/
public class Prog2
{
    /*
     * Method: main
     * Purpose: handles the file reading of txt file
     * Parameters:
     * Returns: none
     */
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

            while(line != null)
            {
                //Scanner in = new Scanner(line);
                //in.close();
                // read in the words from txt file

                // Presorting each word by alphabetical order of the characters
                // the presort here gives the signature for that word: aet (signature) -> eat, ate, tea
                // quicksort or mergesort

                line = br.readLine();
            }

            br.close();
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        
        
        // Sorting the presorted word
        // sort the words by their signature

        // Comparing the adjacent words to find anagram
        // finding presorted element non-uniqueness b/c ate & tea presorted -> aet
        // comparing the signatures
    }
}
