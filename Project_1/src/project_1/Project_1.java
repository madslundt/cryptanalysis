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
        
        FA.Freq(path);
        
        // Read input file
        
        // Start program
        decryption.start();
    }
    
}
