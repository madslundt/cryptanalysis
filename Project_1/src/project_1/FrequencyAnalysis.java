/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

    public String TexTCleanUp(String path)
    {
        String text = "";
        String res = "";
        Scanner scanner;
        try
        {
            scanner = new Scanner(new File(path));
        } catch (FileNotFoundException ex)
        {
            return res;
        }
        Pattern p = Pattern.compile("[A-Z]+");
        while (scanner.hasNext())
        {
            text = scanner.next().toUpperCase();
            for (int i = 0; i < text.length(); i++)
            {
                String s = "" + text.charAt(i);
                Matcher m = p.matcher(s);
                if (m.find())
                {
                    res += s;
                }
            }
        }
        //System.out.println(res);
        return res;
    }

    public HashMap<String, Integer> Freq(String s)
    {
        HashMap<String, Integer> frequence = new HashMap<>();
        char[] c = s.toCharArray();
        String l1, l2 = "", l3 = "";

        for (int i = 0; i < s.length(); i++)
        {
            l1 = c[i] + "";
            if (frequence.containsKey(l1))
            {
                frequence.put(l1, frequence.get(l1) + 1);
            } else
            {
                frequence.put(l1, 1);
            }

            if (i + 1 < s.length())
            {
                l2 = l1 + c[i + 1] + "";

                if (frequence.containsKey(l2))
                {
                    frequence.put(l2, frequence.get(l2) + 1);
                } else
                {
                    frequence.put(l2, 1);
                }

            }
            if (i + 2 < s.length())
            {
                l3 = l2 + c[i + 2] + "";

                if (frequence.containsKey(l3))
                {
                    frequence.put(l3, frequence.get(l3) + 1);
                } else
                {
                    frequence.put(l3, 1);
                }

            }
        }

        //System.out.println(frequence.toString());
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
    
    public void BestFreqs(HashMap<String,Integer> h, int m)
    {
        String[] sA = new String[m];
        String[] sB = new String[m];
        String[] sC = new String[m];
        int[] fA = new int[m];
        int[] fB = new int[m];
        int[] fC = new int[m];
        
        
        Set comb = h.keySet();
        
        for(Object o : comb)
        {
            String s = o.toString();
            int l = s.length();
            switch (l)
            {
                case 1:
                    for(int i = 0; i < m; i++)
                    {
                        if(h.get(s) > fA[i])
                        {
                            sA[i] = s;
                            fA[i] = h.get(s);
                            break;
                        }
                    }
                    break;
                case 2:
                    for(int j = 0; j < m; j++)
                    {
                        if(h.get(s) > fB[j])
                        {
                            sB[j] = s;
                            fB[j] = h.get(s);
                            break;
                        }
                    }
                    break;
                case 3:
                    for(int k = 0; k < m; k++)
                    {
                        if(h.get(s) > fC[k])
                        {
                            sC[k] = s;
                            fC[k] = h.get(s);
                            break;
                        }
                    }
                    break;
            }
        }
        
        
        for(int i = 0; i < m; i++)
        {
            System.out.println(sA[i] + " : " + fA[i]);
        }
        System.out.println();
        for(int j = 0; j < m; j++)
        {
            System.out.println(sB[j] + " : " + fB[j]);
        }
        System.out.println();
        for(int k = 0; k < m; k++)
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
