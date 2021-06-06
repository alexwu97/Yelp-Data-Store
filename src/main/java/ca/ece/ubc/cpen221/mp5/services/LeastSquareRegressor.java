package ca.ece.ubc.cpen221.mp5.services;

import java.util.List;

public class LeastSquareRegressor<X extends Number, Y extends Number> {
    private List<X> input;
    private List<Y> output;
    private double Sxx;
    private double Syy;
    private double Sxy;
    private double meanX;
    private double meanY;
    private double b;
    private double a;

    public boolean generateRegressionModel(List<X> input, List<Y> output){
        this.input = input;
        this.output = output;
        meanX = calculateMean((List<Number>) input);
        meanY = calculateMean((List<Number>) output);
        Sxx = calculateSxx();
        if(Sxx == 0){
            return false;
        }
        Sxy = calculateSxy();
        b = calculateB();
        a = calculateA();
        return true;
    }

    public double predictOutput(Number value){
        if(Sxx == 0.0){
            throw new IllegalArgumentException();
        }
        return b * (double) value + a;
    }

    private double calculateB(){
        return Sxy / Sxx;
    }

    private double calculateA(){
        return meanY - b * meanX;
    }

    private double calculateSxx(){
        return calculateSxxyy((List<Number>) input, meanX);
    }

    private double calculateSyy(){
        return (Syy = calculateSxxyy((List<Number>) output, meanY));
    }

    private double calculateSxxyy(List<Number> list, Double mean){
        if(list == null || list.isEmpty()){
            return 0.0d;
        }

        double s = 0;
        for(Number val : input){
            s += Math.pow(val.doubleValue() - mean, 2);
        }
        return s;
    }

    private double calculateMean(List<Number> list){
        double mean = 0;

        for(Number val : list){
            mean += val.doubleValue();
        }

        return mean / list.size();
    }

    private double calculateSxy(){
        if(input == null || input.isEmpty() || output == null || output.isEmpty()){
            return 0.0d;
        }

        double Sxy = 0;
        for(int i = 0; i < input.size(); i++){
            Sxy += (input.get(i).doubleValue() - meanX)*(output.get(i).doubleValue() - meanY);
        }
        return Sxy;
    }
}
