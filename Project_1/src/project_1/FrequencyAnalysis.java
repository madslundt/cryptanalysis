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
public class FrequencyAnalysis
{

    private String path;
    private Integer counter;
    private HashMap<Character, Double> theoreticalFrequencies = new HashMap<Character, Double>();
    

    public FrequencyAnalysis(String path)
    {
        this.path = path;
        File file = new File(path);
        if (file.isDirectory())
        {
            this.path = file + "/input.txt";
        }
        counter = 0;
        theoreticalFrequencies.put('A', 0.082);
        theoreticalFrequencies.put('B', 0.015);
        theoreticalFrequencies.put('C', 0.028);
        theoreticalFrequencies.put('D', 0.043);
        theoreticalFrequencies.put('E', 0.127);
        theoreticalFrequencies.put('F', 0.022);
        theoreticalFrequencies.put('G', 0.020);
        theoreticalFrequencies.put('H', 0.061);
        theoreticalFrequencies.put('I', 0.070);
        theoreticalFrequencies.put('J', 0.002);
        theoreticalFrequencies.put('K', 0.008);
        theoreticalFrequencies.put('L', 0.040);
        theoreticalFrequencies.put('M', 0.024);
        theoreticalFrequencies.put('N', 0.067);
        theoreticalFrequencies.put('O', 0.075);
        theoreticalFrequencies.put('P', 0.019);
        theoreticalFrequencies.put('Q', 0.001);
        theoreticalFrequencies.put('R', 0.060);
        theoreticalFrequencies.put('S', 0.063);
        theoreticalFrequencies.put('T', 0.091);
        theoreticalFrequencies.put('U', 0.028);
        theoreticalFrequencies.put('V', 0.010);
        theoreticalFrequencies.put('W', 0.023);
        theoreticalFrequencies.put('X', 0.001);
        theoreticalFrequencies.put('Y', 0.020);
        theoreticalFrequencies.put('Z', 0.001);
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
        writer.print("");
        while (scanner.hasNext())
        {
            text = scanner.next().toUpperCase();
            for (int i = 0; i < text.length(); i++)
            {
                Matcher m = p.matcher(text.charAt(i) + "");
                if (m.find())
                {
                    writer.append(text.charAt(i) + "");
                    counter++;
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
        writer.flush();
        writer.close();
        scanner.close();
        //System.out.println("Text length: "+counter);
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
    
    
    public void LetterFreqs(HashMap<String, Integer> h)
    {
        Set comb = h.keySet();
        System.out.println();
        for (Object o : comb)
        {
            String s = o.toString();
            if(s.length() == 1)
            {
                double dA = (double) h.get(s)/counter;
                Character c = s.charAt(0);
                System.out.print(s + " : ");
                System.out.printf("%.3f",dA);
                System.out.print(" >< ");
                System.out.printf("%.3f\n", theoreticalFrequencies.get(c));
            }
            else
            {
                continue;
            }
            
            }
    }
    
    

    public void BestFreqs(HashMap<String, Integer> h, int m)
    {
        String[] sB = new String[m];
        String[] sC = new String[m];
        int[] fB = new int[m];
        int[] fC = new int[m];


        Set comb = h.keySet();

        for (Object o : comb)
        {
            String s = o.toString();
            int l = s.length();
            switch (l)
            {
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
        
        for (int j = 0; j < m; j++)
        {
            double dB = (double) fB[j]/counter;
            System.out.println(sB[j] + " : " + fB[j] + " : " + dB);
        }
        System.out.println();
        for (int k = 0; k < m; k++)
        {
            double dC = (double) fC[k]/counter;
            System.out.println(sC[k] + " : " + fC[k] + " : " + dC);
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
