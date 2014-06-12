package project_1;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 *
 * @author madslundt
 */
public class Project_1 {
    
    private static HashMap<Character, Character> cipherKey = new HashMap<Character, Character>();
    private static HashMap<String, Integer> letterFrequencies = new HashMap<String, Integer>(); 

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FrequencyAnalysis FA = new FrequencyAnalysis(args[0]);
        letterFrequencies = FA.Freq();
        
        Decryption decryption = new Decryption();
        Scanner reader;
        char option;
        while (true) {
            reader = new Scanner(System.in);
            System.out.println("\t1. Display cipher- and decryptiontext.");
            System.out.println("\t2. Update ciphertext.");
            System.out.println("\t3. Display letter frequencies");
            System.out.println("\t4. Display most frequent diagrams and trigrams\n");
            System.out.println("\t0. Undo previous assignment");
            System.out.println("\t!: Quit");
            System.out.print("Please choose one of the options[0-4]: ");
            option = Character.toLowerCase(reader.findInLine(".").charAt(0));
            switch (option) {
                case '1':
                    decryption.displayCipherAndDecryption(args[0]);
                    break;
                case '2':
                    if (decryption.updateCipher(cipherKey)) {
                        
                    } else {
                        System.out.println("No cipher keys found");
                    }
                    break;
                case '3':
                    decryption.displayLetterFrequencies();
                    break;
                case '4':
                    decryption.displayMostFrequentDiagrams();
                    break;
                case '0':
                    if (decryption.undoLastAssignment()) {

                    } else {
                        System.out.println("No previous assignments found.");
                    }
                    break;
                case '!':
                    return;
                default:
                    System.out.println("No option for that.");
            }
            System.out.println("\n\n");
        }
    }
    
}
