package Anagrams;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


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
            String word = br.readLine();

            TreeMap<String, Set<String>> anagramMap = new TreeMap<>();
            int count = 0;
            while(word != null || count < 100)
            {
                //Scanner in = new Scanner(line);
                //in.close();
                // read in the words from txt file
                // take word and toLowerCase() it
                // pass this word to a funtion to get its signature
                // Presorting each word by alphabetical order of the characters
                // the presort here gives the signature for that word: aet (signature) -> eat, ate, tea
                // quicksort or mergesort
                String key = sortString(word);
                //System.out.println(key);
                // store the word and signature
                // hashmap (key: signature, value: set of words)
                insertToMap(anagramMap, key, word);

                count += 1;
                word = br.readLine();
            }
            System.out.println("Anagrams");
            br.close();
            
            int countGroup = 0;
            int max = 0;
            int size = 0;
            Set<String> longest = new HashSet<>();
            for (Map.Entry<String, Set<String>> anagrams : anagramMap.entrySet())
            {
                size = anagrams.getValue().size();
                if (size > 1)
                {
                    if (size > max)
                    {
                        max = size;
                        longest = anagrams.getValue();
                    }
                    System.out.print(anagrams.getKey() + ": ");
                    System.out.println(anagrams.getValue());
                    countGroup+=1;
                }
            }
            System.out.println("There are " + countGroup + " anagram groups");
            System.out.print("The longest anagram group contains " + max + " anagrams. ");
            System.out.println(longest);

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
    /*
     * Method: sortString
     * Purpose: converts a string
     * into a char array and sorts it alphabetically
     * Parameters:
     * String word - the string to convert into array and sort
     * Returns: a sorted string - the alphabetical sorted characters
     * of the word
     */
    public static String sortString(String word)
    {
        char[] charArray = word.toLowerCase().toCharArray();
        Arrays.sort(charArray);
        String signature = String.valueOf(charArray);
        //signature = signature.replaceAll("[^a-zA-Z]", "");
        return signature;
    }

    public static void insertToMap(Map<String, Set<String>> map, String key, String value)
    {
        // Check if the key is already present
        if (!map.containsKey(key)) {
            // If the key is not present, create a new HashSet for the key
            map.put(key, new HashSet<>());
        }
        // Add the value to the set associated with the key
        map.get(key).add(value);
    }
}