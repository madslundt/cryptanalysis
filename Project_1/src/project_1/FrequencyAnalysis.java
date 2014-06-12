/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Mikkel
 */
public class FrequencyAnalysis
{
    private String path;
    public FrequencyAnalysis(String path) {
        this.path = path;
        File file = new File(path);
        if (file.isDirectory()) {
            this.path = file + "/input.txt";
        }
    }

    public HashMap<String, Integer> Freq()
    {
        HashMap<String, Integer> frequence = new HashMap<>();
        String text = "";
        Scanner scanner;
        char[] c = null;

        try
        {
            scanner = new Scanner(new File(path));
        } catch (FileNotFoundException ex)
        {
            return frequence;
        }
        Pattern p = Pattern.compile("^[A-Za-z]+$");

        while (scanner.hasNext())
        {
            text = scanner.next().toUpperCase();
            for (int i = 0; i < text.length(); i++)
            {
                String l1 = "" + text.charAt(i);
                Matcher m = p.matcher(l1 + "");
                if (m.find())
                {
                    if (frequence.containsKey(l1))
                    {
                        frequence.put(l1, frequence.get(l1) + 1);
                    } else
                    {
                        frequence.put(l1, 1);
                    }
                }
                if (i + 1 < text.length())
                {
                    String l2 = "" + text.charAt(i) + text.charAt(i + 1);
                    m = p.matcher(l2 + "");
                    if (m.find())
                    {
                        if (frequence.containsKey(l2))
                        {
                            frequence.put(l2, frequence.get(l2) + 1);
                        } else
                        {
                            frequence.put(l2, 1);
                        }
                    }
                }
                if (i + 2 < text.length())
                {
                    String l3 = "" + text.charAt(i) + text.charAt(i + 1) + text.charAt(i + 2);
                    m = p.matcher(l3 + "");
                    if (m.find())
                    {
                        if (frequence.containsKey(l3))
                        {
                            frequence.put(l3, frequence.get(l3) + 1);
                        } else
                        {
                            frequence.put(l3, 1);
                        }
                    }
                }
            }
        }
        System.out.println(frequence);
        /*
         while (m.find())
         {
         String str = m.group();
         //text = text.replaceAll("\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\_\\+\\-\\{\\}\\|\\;\\,\\.\\?\\<\\>\\[\\]", "");
         text = text.replaceAll("\\W", "");
         c = text.toCharArray();
            

         }
        
         for(int i = 0; i < c.length; i++)
         {
         System.out.println("Char["+i+"]: " + c[i]);;
         }
         */

        return frequence;
    }
    
    public HashMap<Character, Character> getCipherKeys(List<Character> alphabet, HashMap<String, Integer> freq) {
        HashMap<Character, Character> cipherKeys = new HashMap<Character, Character>();
        List<Character> a = new ArrayList<Character>(alphabet);
        HashMap<String, Integer> freq_clone;
        freq_clone = (HashMap) freq.clone();
        
        for (int i = 0; i < freq_clone.size(); i++) {
            int max = 0;
            char c = '!';
            for (Map.Entry<String, Integer> f : freq_clone.entrySet()) {
                // Only characters
                //System.out.println(f.getKey());
                if (f.getKey().length() == 1 && f.getValue() > max) {
                    max = f.getValue();
                    c = f.getKey().charAt(0);
                    //System.out.println(c);
                }
            }
            if (a.size() < 1 || max < 1) {
                return cipherKeys;
            }
            if (c == '!' || !freq.containsKey("" + c)) {
                System.out.println("An error occurred : " + c);
                return cipherKeys;
            }
            cipherKeys.put(c, a.get(0));
            a.remove(0);
            freq_clone.put("" + c, 0);
        }
        
        return cipherKeys;
        
    }
}