
package com.progressoft.tools;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.math.BigDecimal;
import java.nio.file.Path;

import static java.math.RoundingMode.HALF_EVEN;


public class NormalizerImp implements Normalizer{
    
    
    @Override
    public ScoringSummary minMaxScaling(Path csvPath, Path destPath, String colToNormalize){
        
        FileHandler file = new FileHandler(csvPath, destPath, colToNormalize);
        file.indixOfColumn();
         int [] ArrOfElement = file.readElements();
          
        StatCalculater ststcs = new  StatCalculater();
        
        ScoringSummary summary = ststcs.calculateSummary(ArrOfElement);
        
        double [] newElements = new double [file.getNumOfElements()];
        
        for (int i = 1; i < file.getNumOfElements(); i++) { 
                double numrt = ((double)ArrOfElement[i] - summary.min().doubleValue());
                double den = summary.max().doubleValue() -summary.min().doubleValue();
                newElements[i] = numrt / den;
            }
        
        file.writeTo(newElements, "_mm");
    return summary;   
    }
    
    @Override
    public ScoringSummary zscore(Path csvPath, Path destPath, String colToStandardize){
             FileHandler file = new FileHandler(csvPath, destPath, colToStandardize);
             file.indixOfColumn();
             int [] ArrOfElement = file.readElements();
             StatCalculater ststcs = new  StatCalculater();
             ScoringSummary summary = ststcs.calculateSummary(ArrOfElement);
             
             
             double [] newElements = new double [file.getNumOfElements()];
            for (int i = 1; i < file.getNumOfElements(); i++) {
                BigDecimal numrt =new BigDecimal(ArrOfElement[i]).subtract(summary.mean());
                newElements[i] = numrt.divide(summary.standardDeviation(),2,HALF_EVEN).doubleValue();
            }
            
            file.writeTo(newElements, "_z");
            return summary;
    }
    
    
}
