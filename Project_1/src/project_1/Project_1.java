/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project_1;

/**
 *
 * @author madslundt
 */
public class Project_1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FrequencyAnalysis FA = new FrequencyAnalysis();
        
        String path = FA.fileChooser();
        
        FA.Freq(path);
    }
    
}
