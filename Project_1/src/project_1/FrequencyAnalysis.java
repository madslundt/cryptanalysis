/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;

/**
 *
 * @author Mikkel
 */
public class FrequencyAnalysis
{

    public HashMap<String, Integer> Freq(String path)
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
        Pattern p = Pattern.compile("[A-Za-z]");

        while (scanner.hasNext())
        {
            text = scanner.next().toUpperCase();
            for (int i = 0; i < text.length(); i++)
            {
                String l1 = "" + text.charAt(i);
                Matcher m = p.matcher(l1 + "");
                if (m.find())
                {
                    System.out.println("Char: " + l1);
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
                        System.out.println("Char: " + l2);
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
                        System.out.println("Char: " + l3);
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
        System.out.println(frequence.toString());


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

    public String fileChooser()
    {
        String path = "";
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            return fc.getSelectedFile().getPath();
        }
        return path;
    }
}
