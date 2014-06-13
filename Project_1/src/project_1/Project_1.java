package project_1;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 *
 * @author madslundt
 */
public class Project_1 {
    
    public static HashMap<Character, Character> cipherKey = new HashMap<Character, Character>();
    public static HashMap<Character, Character> letterFrequencies = new HashMap<Character, Character>(); 

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FrequencyAnalysis FA = new FrequencyAnalysis();
        
        String path = FA.fileChooser();
        
        float st = System.nanoTime();
        System.out.println("STEP 1 INITIATED: STARTED CLEANING TEXT");
        String text = FA.TexTCleanUp(path);
        System.out.println("STEP 1 COMPLETED IN "+ ((System.nanoTime()-st)/1000000000) +" SECONDS\nSTEP 2 INITIATED: STARTED FREQUENCY ANALYSIS");
        st = System.nanoTime();
        HashMap<String, Integer> h = FA.Freq(text);
        System.out.println("STEP 2 COMPLETED IN "+ ((System.nanoTime()-st)/1000000000) +" SECONDS\nSTEP 3 INITIATED: START BEST FREQUENCIES SEARCH");
        st = System.nanoTime();
        FA.BestFreqs(h, 5);
        System.out.println("STEP 3 COMPLETED IN "+ ((System.nanoTime()-st)/1000000000) +" SECONDS");
        // Read input file
        
        // Start program
        //decryption.start();
    }
    
}
