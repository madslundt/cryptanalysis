/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Decryption {

    // HashMap to save old content (for the undo)
    private static HashMap<Character, Character> oldCipherKeys = new HashMap<Character, Character>();

    private String path; // Stores path

    /**
     * Constructor
     *
     * @param path String
     */
    public Decryption(String path) {
        File file = new File(path);
        if (file.isFile()) {
            path = file.getParent();
        }
        this.path = path;
    }

    /**
     * Creates a text file with ciphertext and plaintext.
     *
     * @param cipherKey
     */
    public void displayCipherAndDecryption(HashMap<Character, Character> cipherKey) {
        decryption(cipherKey);
    }

    /**
     * Private: Writes a text file with ciphertext and plaintext.
     *
     * @param cipherKey HashMap<Character, Character> that is going to be used
     */
    private void decryption(HashMap<Character, Character> cipherKey) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path + "/cleanedfile.txt"));
            PrintWriter writer = new PrintWriter(path + "/decryption.txt", "UTF-8");
            String line = br.readLine();
            writer.print("");
            while (line != null) {
                writer.append(line + "\n"); // Writing text from file
                for (int i = 0; i < line.length(); i++) {
                    writer.append(Character.toLowerCase(cipherKey.get(line.charAt(i)))); // Decrypting each character for the line before 
                }
                writer.append("\n");
                line = br.readLine();
            }
            writer.flush();
            writer.close();
            br.close();
        } catch (IOException e) {
            System.out.println("An error occurred in decryption.");
        }
    }

    /**
     * Updates cipher key
     *
     * @param cipherKey HashMap<Character, Character>
     * @return boolean false if an error occurres.
     */
    public boolean updateCipher(HashMap<Character, Character> cipherKey) {
        if (cipherKey.isEmpty()) {
            return false;
        }
        
        // Makes sure to clear oldCipherKeys before putting new values into.
        oldCipherKeys.clear();
        for (Map.Entry<Character, Character> c : cipherKey.entrySet()) {
            oldCipherKeys.put(c.getKey(), c.getValue());
        }
        
        Scanner reader;
        char key, val;
        while (true) {
            for (Map.Entry<Character, Character> c : cipherKey.entrySet()) {
                System.out.println(c.getKey() + "\t=>\t" + c.getValue());
            }

            System.out.print("Enter the key you want to change: ");
            reader = new Scanner(System.in);
            key = reader.findInLine(".").charAt(0);
            if (key == '!') {
                break;
            }
            if (!cipherKey.containsKey(key)) {
                System.out.println("Couldn't find cipher key " + key);
                continue;
            }
            System.out.print("Enter the value for key " + key + ": ");
            reader = new Scanner(System.in);
            val = Character.toLowerCase(reader.findInLine(".").charAt(0));
            if (val == '!') {
                continue;
            }
            updateCipherkeys(key, val, cipherKey);
        }
        decryption(cipherKey);
        return true;
    }

    /**
     * Private function: Updates a cipherkey and makes sure there are no keys
     * with same value.
     *
     * @param key char
     * @param val char
     * @param cipherKey HashMap<Character, Character> to be changed
     */
    private void updateCipherkeys(char key, char val, HashMap<Character, Character> cipherKey) {
        // Checking if there is a key assigned to the updated value.
        for (Map.Entry<Character, Character> c : cipherKey.entrySet()) {
            if (c.getValue() == val) {
                // If there is a value assigned to a key, it will take the 
                //value from the key to be changed and insert into this key.
                cipherKey.put(c.getKey(), cipherKey.get(key));
                break;
            }
        }
        cipherKey.put(key, val);
    }

    /**
     * Prints the 10 most used di- or trigrams.
     *
     * @param letterFrequencies HashMap<String, Integer>
     * @param n int - 2 if digrams and 3 if trigrams.
     */
    public void displayMostFrequentDiTrigrams(HashMap<String, Integer> letterFrequencies, int n) {
        if (n != 2 && n != 3) {
            throw new IllegalArgumentException("n has to be either 2 or 3");
        }
        
        String[] s1 = new String[10];
        int[] f1 = new int[10];

        Set comb = letterFrequencies.keySet();
        
        // Finds the n most used di or trigrams.
        for (Object o : comb) {
            String s = o.toString();
            if (s.length() == n) {
                for (int j = 0; j < f1.length; j++) {
                    if (letterFrequencies.get(s) > f1[j]) {
                        s1[j] = s;
                        f1[j] = letterFrequencies.get(s);
                        break;
                    }
                }
            }
        }
        // Prints the di- or trigames in order (descending).
        int count = 0;
        for (int j = 0; j < f1.length; j++) {
            if (s1[j] == null) {
                break;
            }
            System.out.println(count++ + ": " + s1[j] + " (" + f1[j] + ")");
        }
    }

    /**
     * Undo last assigment if assigments found (else return false).
     *
     * @return bool
     */
    public boolean undoLastAssignment(HashMap<Character, Character> cipherKey) {
        if (oldCipherKeys.isEmpty()) {
            return false;
        }
        
        // Makes sure to clear the current cipherKeys when restoring to the old.
        cipherKey.clear();
        for (Map.Entry<Character, Character> c : oldCipherKeys.entrySet()) {
            cipherKey.put(c.getKey(), c.getValue());
        }
        decryption(cipherKey); // Run decryption with the 'new' cipherKeys.
        return true;
    }
}