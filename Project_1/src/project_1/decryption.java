/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import static project_1.Project_1.cipherKey;

/**
 *
 * @author madslundt
 */
public class decryption {
    public static void start() {
        Scanner reader;
        char option;
        while (true) {
            reader = new Scanner(System.in);
            System.out.println("\t1. Display cipher- and decryptiontext.");
            System.out.println("\t2. Update ciphertext.");
            System.out.println("\t3. Display letter frequencies");
            System.out.println("\t4. Display most frequent diagrams and trigrams\n");
            System.out.println("\t0. Undo previous assignment");
            System.out.println("\tq: Quit");
            System.out.print("Please choose one of the options[0-4]: ");
            option = Character.toLowerCase(reader.findInLine(".").charAt(0));
            switch (option) {
                case '1':
                    displayCipherAndDecryption();
                    break;
                case '2':
                    if (updateCipher()) {
                        
                    } else {
                        System.out.println("No cipher keys found");
                    }
                    break;
                case '3':
                    displayLetterFrequencies();
                    break;
                case '4':
                    displayMostFrequentDiagrams();
                    break;
                case '0':
                    if (undoLastAssignment()) {

                    } else {
                        System.out.println("No previous assignments found.");
                    }
                    break;
                case '!':
                    return;
                default:
                    System.out.println("No option for that.");
            }
            System.out.println("\n\n");
        }
    }
    
    /**
     * Displays cipher and decryption from the input text.
     */
    public static void displayCipherAndDecryption() {
        try  {
            BufferedReader br = new BufferedReader(new FileReader("output.txt"));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }
        } catch(IOException e) {
            decryption();
            displayCipherAndDecryption();
        }
        
    }
    
    private static void decryption() {
        HashMap<Character, Character> cipherKey = Project_1.cipherKey;
        try  {
            BufferedReader br = new BufferedReader(new FileReader("output.txt"));
            PrintWriter writer = new PrintWriter("cipher_decryption.txt", "UTF-8");
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            writer.write("");
            while (line != null) {
                writer.append(line + "\n");
                String str = "";
                for (int i = 0; i < str.length(); i++) {
                    str += Character.toLowerCase(cipherKey.get(str.charAt(i)));
                }
                writer.append(str + "\n");
                line = br.readLine();
            }
        } catch(IOException e) {

        }
    }
    
    /**
     * Update the cipher.
     */
    public static boolean updateCipher() {
        HashMap<Character, Character> cipherKey = Project_1.cipherKey;
        if (cipherKey.isEmpty()) {
            return false;
        }
        Scanner reader;
        char key, val;
        while (true) {
            for (Map.Entry<Character, Character> c : cipherKey.entrySet()) {
                System.out.println(c.getKey() + "\t=>\t" + c.getValue());
            }

            System.out.print("Enter the key you want to change: ");
            reader = new Scanner(System.in);
            key = Character.toLowerCase(reader.findInLine(".").charAt(0));
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
            updateCipherkeys(key, val);
        }
        decryption();
        return true;
    }
    
    private static void updateCipherkeys(char key, char val) {
        HashMap<Character, Character> cipherKey = Project_1.cipherKey;
        for (Map.Entry<Character, Character> c : cipherKey.entrySet()) {
            if (c.getValue() == val) {
                cipherKey.put(c.getKey(), cipherKey.get(key));
                break;
            }
        }
        cipherKey.put(key, val);
    }
    
    /**
     * Display letter frequencies.
     */
    public static void displayLetterFrequencies() {
        
    }
    
    /**
     * Display most frequent diagrams
     */
    public static void displayMostFrequentDiagrams() {
        
    }
    
    /**
     * Undo last assigment if assigments found (else return false).
     * @return bool
     */
    public static boolean undoLastAssignment() {
        
        return false;
    }
}
