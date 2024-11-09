package Anagrams;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.Map;
import java.text.Normalizer;

/**************************************************************/
/* Benjamin Nguyen                                            */
/* Student ID: 016068702                                      */
/* CS 3310, Fall 2024                                         */
/* Programming Assignment 2                                   */
/* Prog2 class: finds anagrams in a text file and displays    */
/* the outputed anagram sets in a text file                   */
/**************************************************************/
public class Prog2
{
    /*
     * Method: main
     * Purpose: handles the file reading of txt file and calls 
     * the methods for finding and displaying anagrams
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
            // Read in a word from the inputed file
            BufferedReader br = new BufferedReader(new InputStreamReader
                                (new FileInputStream(fileName), "utf-8"));
            userInput.close();
            String word = br.readLine();

            // Use a hashmap to store a set of anagrams as the values
            TreeMap<String, Set<String>> anagramMap = new TreeMap<>();

            while (word != null)
            {
                String key = sortString(word);

                insertToMap(anagramMap, key, word);
                // read in the next word
                word = br.readLine();
            }
            br.close();

            printAnagramToFile(anagramMap);
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    /*
     * Method: sortString
     * Purpose: converts a string
     * into a char array and sorts it alphabetically
     * Parameters:
     * String word - the string to sort
     * Returns: (a sorted string) signature - the string where its characters
     * have been alphabetically sorted 
     */
    public static String sortString(String word)
    {
        //String normalized = Normalizer.normalize(word, Normalizer.Form.NFD);
        // Remove accents by keeping only ASCII characters (a-zA-Z0-9)
        //normalized = normalized.replaceAll("[^\\p{ASCII}]", "");
        //normalized = normalized.replace("'", "");

        char[] charArray = word.toLowerCase().toCharArray();
        Arrays.sort(charArray);
        String signature = String.valueOf(charArray);
    
        return signature;
    }

    /*
     * Method: insertToMap
     * Purpose: 
     * Parameters:
     * Returns: 
     */
    public static void insertToMap(Map<String, Set<String>> map, String key, String value)
    {
        // Check if key is already present
        if (!map.containsKey(key)) 
        {
            // If the key is not present, create a new HashSet for the key
            map.put(key, new HashSet<>());
        }
        // Add the value(anagram) to the set associated with the its key(signature)
        map.get(key).add(value);
    }

    /*
     * Method: printAnagramToFile
     * Purpose: 
     * Parameters:
     * Returns: 
     */
    public static void printAnagramToFile(Map<String, Set<String>> anagramMap)
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(
                                    new FileWriter("output.txt"));
            
            int groupCount = 0;
            int max = 0;
            int size = 0;
            Set<String> longestAnagram = new HashSet<>();

            System.out.println("Beginning to write to file: output.txt");

            for (Map.Entry<String, Set<String>> anagrams : anagramMap.entrySet())
            {
                size = anagrams.getValue().size();
                /* the anagram set needs to contain more than 1 word
                 to be consider an anagram */
                if (size > 1)
                {
                    if (size > max)
                    {
                        max = size;
                        longestAnagram = anagrams.getValue();
                    }
                    
                    writer.write(anagrams.getKey() + " => ");
                    for (String word : anagrams.getValue())
                    {
                        writer.write(word + "   ");
                    }
                    writer.write("\n");
                    
                    groupCount+=1;
                }
            }
            writer.close();
            System.out.println("There are " + groupCount + " anagram groups");
            System.out.print("The longest anagram group contains " + max + " anagrams. ");
            System.out.println(longestAnagram);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}