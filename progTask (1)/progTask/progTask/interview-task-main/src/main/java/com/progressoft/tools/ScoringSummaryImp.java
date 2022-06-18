

package com.progressoft.tools;


import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Mohammad
 */
public class ScoringSummaryImp implements ScoringSummary{
    
    
    private double min;
    private double max;
    private double mean;
    private double variance;
    private double SD;
    private double median;
    
    
  public ScoringSummaryImp(double min, double max, double mean,
          double variance, double SD, double median) {
        this.min = min;
        this.max = max;
        this.mean = mean;
        this.variance = variance;
        this.SD = SD;
        this.median = median;
    }
    
    @Override
    public BigDecimal min() {
        BigDecimal val = new BigDecimal(min);
//        val = val.setScale(2, RoundingMode.HALF_EVEN);
        return val.setScale(2,RoundingMode.HALF_EVEN);
    }

    @Override
    public BigDecimal max() {
        BigDecimal val = new BigDecimal(max);
//        val = val.setScale(2, RoundingMode.HALF_EVEN);
        return val.setScale(2,RoundingMode.HALF_EVEN);
    }

    @Override
    public BigDecimal variance() {
        BigDecimal val = new BigDecimal(variance);
//        val = val.setScale(2, RoundingMode.HALF_EVEN);
        return val.setScale(2,RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal standardDeviation() {
        BigDecimal val = new BigDecimal(SD);
//        val = val.setScale(2, RoundingMode.HALF_EVEN);
        return val.setScale(2,RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal mean() {
        BigDecimal val = new BigDecimal(mean);
//        val = val.setScale(2, RoundingMode.HALF_EVEN);
        return val.setScale(2,RoundingMode.HALF_EVEN);
    }

    @Override
    public BigDecimal median() {
        BigDecimal val = new BigDecimal(median);
//        val = val.setScale(2, RoundingMode.HALF_EVEN);
        return val.setScale(2,RoundingMode.HALF_EVEN);
    }
    
}

