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

/**
 *
 * @author madslundt
 */
public class Decryption {  
    /**
     * Displays cipher and decryption from the input text.
     */
    private static HashMap<Character, Character> tempHashMap = new HashMap<Character, Character>();
    
    private String path;
    
    public Decryption(String path) {
        File file = new File(path);
        if (file.isFile()) {
            path = file.getParent();
        }
        this.path = path;
    }
    
    public void displayCipherAndDecryption(HashMap<Character, Character> cipherKey) {
        try  {
            decryption(cipherKey);
            BufferedReader br = new BufferedReader(new FileReader(path + "/decryption.txt"));
            String line = br.readLine();
            while (line != null) {
                line = br.readLine();
            }
        } catch(IOException e) {
            System.out.println("An error occurred (decryption error).");
        }
        
    }
    
    private void decryption(HashMap<Character, Character> cipherKey) {
        try  {
            BufferedReader br = new BufferedReader(new FileReader(path + "/cleanedfile.txt"));
            PrintWriter writer = new PrintWriter(path + "/decryption.txt", "UTF-8");
            String line = br.readLine();
            writer.print("");
            while (line != null) {
                writer.append(line + "\n");
                for (int i = 0; i < line.length(); i++) {
                    writer.append(Character.toLowerCase(cipherKey.get(line.charAt(i))));
                }
                writer.append("\n");
                line = br.readLine();
            }
            writer.flush();
            writer.close();
            br.close();
        } catch(IOException e) {
            System.out.println("An error occurred.");
        }
    }
    
    /**
     * Update the cipher.
     */
    public boolean updateCipher(HashMap<Character, Character> cipherKey) {
        tempHashMap.clear();
        for (Map.Entry<Character, Character> c : cipherKey.entrySet()) {
            tempHashMap.put(c.getKey(), c.getValue());
        }
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
    
    private void updateCipherkeys(char key, char val, HashMap<Character, Character> cipherKey) {
        for (Map.Entry<Character, Character> c : cipherKey.entrySet()) {
            if (c.getValue() == val) {
                cipherKey.put(c.getKey(), cipherKey.get(key));
                break;
            }
        }
        cipherKey.put(key, val);
    }
    
    /**
     * Display most frequent diagrams
     */
    public void displayMostFrequentDiTrigrams(HashMap<String, Integer> letterFrequencies, int n) {
        Set comb = letterFrequencies.keySet();
        int count = 0;
        for (Object o : comb)
        {
            String s = o.toString();
            if (s.length() == n) {
                System.out.println(count++ + ": " + s);
            }
        }
    }
    
    /**
     * Undo last assigment if assigments found (else return false).
     * @return bool
     */
    public boolean undoLastAssignment(HashMap<Character, Character> cipherKey) {
        if (tempHashMap.isEmpty()) {
            return false;
        }
        cipherKey.clear();
        for (Map.Entry<Character, Character> c : tempHashMap.entrySet()) {
            cipherKey.put(c.getKey(), c.getValue());
        }
        decryption(cipherKey);
        return true;
    }
}
