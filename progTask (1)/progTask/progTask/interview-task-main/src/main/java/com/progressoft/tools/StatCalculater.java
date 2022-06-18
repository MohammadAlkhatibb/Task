package com.progressoft.tools;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;

public class StatCalculater {
    
    public ScoringSummary calculateSummary(int[] arr){
        return new ScoringSummaryImp(findMin(arr),findMax(arr),
                findMean(arr),findVariance(arr),Math.sqrt(findVariance(arr)),findMedian(arr));
    }
    
     public int findMin ( int [] arr) {
        
        int min =  arr[1];
         
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min)
                min= arr[i];
        }
        return min;
    }

    public  int findMax( int [] arr) {
        int max = arr[1];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max)
                max= arr[i];
        }
        return max;
    }



    public double findMean (int [] arr){
        double numOfElements = arr.length -1;
        double sumOfElements = 0;
        for (int i = 1; i < arr.length; i++) {
            sumOfElements = sumOfElements + arr[i];
        }
        BigDecimal m= new BigDecimal(sumOfElements/numOfElements);
        return m.setScale(0, RoundingMode.HALF_EVEN).doubleValue();
    }

    public double findVariance (int [] arr) {
        double numOfElements = arr.length -1;
        double numeratorSum=0;
        double distanceToMean;
        for (int i = 1; i < arr.length; i++) {
            distanceToMean =  findMean(arr)- arr[i];
            distanceToMean = Math.pow(distanceToMean, 2f);
            numeratorSum = numeratorSum + distanceToMean;
        }
        BigDecimal v = new BigDecimal ( numeratorSum/(numOfElements));
        return v.setScale(0, RoundingMode.HALF_EVEN).doubleValue();
    }


    public double findMedian (int [] arr){
        int [] temp = new int[arr.length-1];
        for (int i = 0; i < arr.length-1; i++) {
            temp[i] = arr[i+1];
        }
    //   int [] temp = arr;
         Arrays.sort(temp);
        
        if (temp.length%2 ==0){
            double mid1,mid2;
            mid1 = temp [temp.length/2];
            mid2 = temp [(temp.length/2) -1];
            return ((mid1+mid2)/2);
        }
        else{
            return (temp[(temp.length-1)/2]);
        }
    }
}
