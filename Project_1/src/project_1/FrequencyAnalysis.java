/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Mikkel
 */
public class FrequencyAnalysis {

    private String path; // Stores path
    private Integer counter;
    // The english alphabeat with theoretical values.
    private HashMap<Character, Double> theoreticalFrequencies = new HashMap<Character, Double>() {
        {
            put('A', 0.082);
            put('B', 0.015);
            put('C', 0.028);
            put('D', 0.043);
            put('E', 0.127);
            put('F', 0.022);
            put('G', 0.020);
            put('H', 0.061);
            put('I', 0.070);
            put('J', 0.002);
            put('K', 0.008);
            put('L', 0.040);
            put('M', 0.024);
            put('N', 0.067);
            put('O', 0.075);
            put('P', 0.019);
            put('Q', 0.001);
            put('R', 0.060);
            put('S', 0.063);
            put('T', 0.091);
            put('U', 0.028);
            put('V', 0.010);
            put('W', 0.023);
            put('X', 0.001);
            put('Y', 0.020);
            put('Z', 0.001);
        }
    };

    /**
     * Constructor
     *
     * @param path String
     */
    public FrequencyAnalysis(String path) {
        this.path = path;
        File file = new File(path);
        if (file.isDirectory()) {
            this.path = file + "/input.txt";
        }
        counter = 0;

    }

    /**
     * Loops through the file with plaintext and finds letter frequency for
     * 1 letter, 2-pair letters and 3 letters in a row
     *
     * @return HashMap<String, Integer>
     */
    public HashMap<String, Integer> Frequency() {
        String text;
        Scanner scanner = null;
        PrintWriter writer = null;
        HashMap<String, Integer> frequence = new HashMap<>();
        String l1, l2 = "", l3 = "";
        try {
            scanner = new Scanner(new File(path));
            File file = new File(path);
            if (file.isFile()) {
                path = file.getParent();
            }

        } catch (FileNotFoundException ex) {
            return frequence;
        }

        Pattern p = Pattern.compile("^[A-Za-z]+$"); // Regex only allows A-Za-z
        try {
            writer = new PrintWriter(path + "/cleanedfile.txt", "UTF-8");
        } catch (IOException e) {
            return frequence;
        }
        writer.print("");
        while (scanner.hasNext()) {
            text = scanner.next().toUpperCase();
            for (int i = 0; i < text.length(); i++) {
                Matcher m = p.matcher(text.charAt(i) + "");
                if (m.find()) {
                    writer.append(text.charAt(i) + "");
                    counter++;
                    l1 = text.charAt(i) + "";
                    if (frequence.containsKey(l1)) {
                        frequence.put(l1, frequence.get(l1) + 1);
                    } else {
                        frequence.put(l1, 1);
                    }

                    if (i + 1 < text.length()) {
                        l2 = l1 + text.charAt(i + 1) + "";
                        m = p.matcher(l2);
                        if (m.find()) {
                            if (frequence.containsKey(l2)) {
                                frequence.put(l2, frequence.get(l2) + 1);
                            } else {
                                frequence.put(l2, 1);
                            }
                        }

                    }
                    if (i + 2 < text.length()) {
                        l3 = l2 + text.charAt(i + 2) + "";
                        m = p.matcher(l3);
                        if (m.find()) {
                            if (frequence.containsKey(l3)) {
                                frequence.put(l3, frequence.get(l3) + 1);
                            } else {
                                frequence.put(l3, 1);
                            }
                        }

                    }
                }
            }
        }
        writer.flush();
        writer.close();
        scanner.close();
        return frequence;
    }

    /**
     * Calculates the cipher keys
     *
     * @param alphabet List<Character> with the whole alphabet (a-z) in order of
     * most used
     * @param freq HashMap<String, Integer> frequency
     * @return HashMap<Character, Character>
     */
    public HashMap<Character, Character> getCipherKeys(List<Character> alphabet, HashMap<String, Integer> freq) {
        HashMap<Character, Character> cipherKeys = new HashMap<Character, Character>();
        List<Character> a = new ArrayList<Character>(alphabet); // Clones the alphabeat into a new list
        
        // Clone the frequency HashMap into a new one. 
        HashMap<String, Integer> freq_clone;
        freq_clone = (HashMap) freq.clone();

        for (int i = 0; i < freq_clone.size(); i++) {
            int max = 0;
            char c = '!';
            for (Map.Entry<String, Integer> f : freq_clone.entrySet()) {
                if (f.getKey().length() == 1 && f.getValue() > max) {
                    max = f.getValue();
                    c = f.getKey().charAt(0);
                }
            }
            if (a.size() < 1 || max < 1) {
                return cipherKeys;
            }
            if (c == '!' || !freq.containsKey("" + c)) {
                System.out.println("An error occurred : " + c);
                return cipherKeys;
            }
            // Insert the most the most used letter from the alphabeat 
            // into the letter in the plaintext that is most used.
            cipherKeys.put(c, a.get(0));
            a.remove(0); // Removes the letter, to mark it used and not to be used on new letter.
            freq_clone.put("" + c, 0);
        }

        return cipherKeys;

    }

    /**
     * Prints the letter frequencies against the theoretical english alphabeat
     *
     * @param h HashMap<String, Integer>
     */
    public void LetterFreqs(HashMap<String, Integer> h) {
        Set comb = h.keySet();
        System.out.println();
        for (Object o : comb) {
            String s = o.toString();
            if (s.length() == 1) {
                double dA = (double) h.get(s) / counter;
                Character c = s.charAt(0);
                System.out.print(s + " : ");
                System.out.printf("%.3f", dA); // Prints value with 3 decimals.
                System.out.print(" >< ");
                System.out.printf("%.3f\n", theoreticalFrequencies.get(c));
            }
        }
    }
}
