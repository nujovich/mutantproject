package com.mutantproject.dto;

public class StatsDto {

    private int countDnaHuman;
    private int countDnaMutant;
    private double ratio;

    public StatsDto() {
        super();
    }

    public StatsDto(int countDnaHuman, int countDnaMutant, double ratio) {
        this.countDnaHuman = countDnaHuman;
        this.countDnaMutant = countDnaMutant;
        this.ratio = ratio;
    }

    public int getCountDnaHuman() {
        return countDnaHuman;
    }

    public void setCountDnaHuman(int countDnaHuman) {
        this.countDnaHuman = countDnaHuman;
    }

    public int getCountDnaMutant() {
        return countDnaMutant;
    }

    public void setCountDnaMutant(int countDnaMutant) {
        this.countDnaMutant = countDnaMutant;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }
    
}