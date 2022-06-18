
package com.progressoft.tools;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FileHandler {
    Path csvPath;
    Path destPath;
    String colName; 
    String data =""; 
    String [] lines;
    // To store indix of column that need to normalize
    int indix;
    
    public FileHandler (Path sCSV, Path dCSV, String colName){
        try {
            csvPath = sCSV;
            destPath = dCSV;
            this.colName = colName;
            data =new String(Files.readAllBytes(sCSV));
            lines = data.split("\\n");
            
        } catch (IOException ex) {
            throw new IllegalArgumentException("source file not found");
        }
    }
    
    public void indixOfColumn () {
        
             String [] firstRow = lines[0].split(",");

            int  indixOfColumn =-1;
            for (int i = 0; i < firstRow.length; i++) {
                
                firstRow[i] = firstRow[i].replaceAll("\\s","");
                if (firstRow[i].equals(colName)){
                  indixOfColumn =i; 
                }        
            }
            if(indixOfColumn == -1)
                throw new IllegalArgumentException("column "+colName+" not found");
        indix = indixOfColumn;    
       // return indixOfColumn; 
    }
    
    public  int [] readElements (){
            int  [] columnCells = new int [lines.length];
            // i = 1 because 0 index refer to first line (non- numeric)
            for (int i = 1; i < lines.length; i++) {
                String [] rowArray = lines[i].split(",");
                rowArray[indix]=rowArray[indix].replaceAll("\\s","");  
                columnCells[i] = Integer.parseInt(rowArray[indix]); 
              //  columnCells[i] = Double.parseDouble(rowArray[indix]);
            }
        return columnCells;
    }
    
    
    public int getNumOfElements () {
        return  lines.length;
    }
    
    public void writeTo (double [] Elements, String str){
        BufferedWriter writer = null;

        try {
            List <String> list = Arrays.asList(lines);

            writer = Files.newBufferedWriter(destPath);

            for (int i = 0; i < list.size(); i++) {
                BigDecimal val = new BigDecimal(Elements[i]);
                String [] line = list.get(i).split(",");
                List<String> record = new ArrayList<String>(Arrays.asList(line));


                if (indix+1 == record.size()){
                    StringBuilder sb = new StringBuilder();
                    for (int k = 0; k < record.size(); k++) {
                        if (k==2)
                            sb.append(record.get(k).replaceAll("\\s",""));
                        else
                            sb.append(record.get(k));
                        sb.append(",");
                    }
                    if (i==0) {
                        sb.append(colName + str);

                        writer.write(sb.toString());

                        writer.append("\n");
                    }
                    else{
                        sb.append(val.setScale(2, RoundingMode.HALF_EVEN).toString());
                        writer.write(sb.toString());
                        writer.append("\n");
                    }
                }
                else {
                    if (i==0)
                        record.add(indix+1, colName+ str);
                    else

                        record.add(indix+1, val.setScale(2, RoundingMode.HALF_EVEN).toString());
                    for (int j = 0; j < record.size()-1; j++) {
                        writer.write(record.get(j)+",");
                    }
                    writer.write(record.get(record.size()-1));
                }
            }
            // writer.close();
        } catch (IOException ex) {
                throw new IllegalArgumentException("the destination file does not exists");
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                throw new IllegalArgumentException("the destination is not a file");
            }
        }
    }
    
    
}
