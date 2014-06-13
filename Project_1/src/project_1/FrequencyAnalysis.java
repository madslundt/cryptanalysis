/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Mikkel
 */
public class FrequencyAnalysis
{

    private String path;

    public FrequencyAnalysis(String path)
    {
        this.path = path;
        File file = new File(path);
        if (file.isDirectory())
        {
            this.path = file + "/input.txt";
        }
    }

    public HashMap<String, Integer> Frequency()
    {
        String text;
        Scanner scanner = null;
        PrintWriter writer = null;
        HashMap<String, Integer> frequence = new HashMap<>();
        String l1, l2 = "", l3 = "";
        try
        {
            scanner = new Scanner(new File(path));
            File file = new File(path);
            if (file.isFile())
            {
                path = file.getParent();
            }

        } catch (FileNotFoundException ex)
        {
            return frequence;
        }

        //Pattern p = Pattern.compile("[A-Z]+");

        Pattern p = Pattern.compile("^[A-Za-z]+$");
        try
        {

            writer = new PrintWriter(path + "/cleanedfile.txt", "UTF-8");
        } catch (IOException e)
        {
            return frequence;
        }
        writer.write("");
        while (scanner.hasNext())
        {
            text = scanner.next().toUpperCase();
            for (int i = 0; i < text.length(); i++)
            {
                Matcher m = p.matcher(text.charAt(i) + "");
                if (m.find())
                {
                    writer.append(text.charAt(i) + "");
                    l1 = text.charAt(i) + "";
                    if (frequence.containsKey(l1))
                    {
                        frequence.put(l1, frequence.get(l1) + 1);
                    } else
                    {
                        frequence.put(l1, 1);
                    }

                    if (i + 1 < text.length())
                    {
                        l2 = l1 + text.charAt(i + 1) + "";
                        m = p.matcher(l2);
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
                        l3 = l2 + text.charAt(i + 2) + "";
                        m = p.matcher(l3);
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
        }

        //System.out.println(res);
        return frequence;
    }

    public HashMap<Character, Character> getCipherKeys(List<Character> alphabet, HashMap<String, Integer> freq)
    {
        HashMap<Character, Character> cipherKeys = new HashMap<Character, Character>();
        List<Character> a = new ArrayList<Character>(alphabet);
        HashMap<String, Integer> freq_clone;
        freq_clone = (HashMap) freq.clone();

        for (int i = 0; i < freq_clone.size(); i++)
        {
            int max = 0;
            char c = '!';
            for (Map.Entry<String, Integer> f : freq_clone.entrySet())
            {
                // Only characters
                //System.out.println(f.getKey());
                if (f.getKey().length() == 1 && f.getValue() > max)
                {
                    max = f.getValue();
                    c = f.getKey().charAt(0);
                    //System.out.println(c);
                }
            }
            if (a.size() < 1 || max < 1)
            {
                return cipherKeys;
            }
            if (c == '!' || !freq.containsKey("" + c))
            {
                System.out.println("An error occurred : " + c);
                return cipherKeys;
            }
            cipherKeys.put(c, a.get(0));
            a.remove(0);
            freq_clone.put("" + c, 0);
        }

        return cipherKeys;

    }

    public void BestFreqs(HashMap<String, Integer> h, int m)
    {
        String[] sA = new String[m];
        String[] sB = new String[m];
        String[] sC = new String[m];
        int[] fA = new int[m];
        int[] fB = new int[m];
        int[] fC = new int[m];


        Set comb = h.keySet();

        for (Object o : comb)
        {
            String s = o.toString();
            int l = s.length();
            switch (l)
            {
                case 1:
                    for (int i = 0; i < m; i++)
                    {
                        if (h.get(s) > fA[i])
                        {
                            sA[i] = s;
                            fA[i] = h.get(s);
                            break;
                        }
                    }
                    break;
                case 2:
                    for (int j = 0; j < m; j++)
                    {
                        if (h.get(s) > fB[j])
                        {
                            sB[j] = s;
                            fB[j] = h.get(s);
                            break;
                        }
                    }
                    break;
                case 3:
                    for (int k = 0; k < m; k++)
                    {
                        if (h.get(s) > fC[k])
                        {
                            sC[k] = s;
                            fC[k] = h.get(s);
                            break;
                        }
                    }
                    break;
            }
        }


        for (int i = 0; i < m; i++)
        {
            System.out.println(sA[i] + " : " + fA[i]);
        }
        System.out.println();
        for (int j = 0; j < m; j++)
        {
            System.out.println(sB[j] + " : " + fB[j]);
        }
        System.out.println();
        for (int k = 0; k < m; k++)
        {
            System.out.println(sC[k] + " : " + fC[k]);
        }


    }
    /*
     private static File fileChooserSave()
     {
     // Make a new filechooser
     JFileChooser fc = new JFileChooser();
     fc.setDialogTitle("Specify a file to save");
     // add a filter
     FileFilter ft = new FileNameExtensionFilter("Text Files", "txt");
     fc.addChoosableFileFilter(ft);
     // setup a default name (the current time)
     DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
     Date date = new Date();
     String file_name = dateFormat.format(date);
     fc.setSelectedFile(new File(file_name + ".txt"));
     // set directory to last directory opened
     fc.setCurrentDirectory(directoryMarker);
     // open the filechooser and if OK is selected select that file
     if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
     {
     directoryMarker = fc.getCurrentDirectory();
     return fc.getSelectedFile();
     }

     return null;
     }
     */
}
