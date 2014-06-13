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
    
    public void displayCipherAndDecryption() {
        try  {
            BufferedReader br = new BufferedReader(new FileReader(path + "/output.txt"));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }
        } catch(IOException e) {
            System.out.println("No file");
        }
        
    }
    
    private void decryption(HashMap<Character, Character> cipherKey) {
        try  {
            BufferedReader br = new BufferedReader(new FileReader(path + "/output.txt"));
            PrintWriter writer = new PrintWriter(path + "cipher_decryption.txt", "UTF-8");
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
     * Display letter frequencies.
     */
    public void displayLetterFrequencies() {
        
    }
    
    /**
     * Display most frequent diagrams
     */
    public void displayMostFrequentDiagrams() {
        
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
        return true;
    }
}
