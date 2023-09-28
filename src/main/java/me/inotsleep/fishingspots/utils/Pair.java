package me.inotsleep.fishingspots.utils;

public class Pair {
    double k;
    double v;

    public double getK() {
        return k;
    }

    public double getV() {
        return v;
    }

    public void setK(double k) {
        this.k = k;
    }

    public void setV(double v) {
        this.v = v;
    }

    public Pair(double k, double v) {
        this.k = k;
        this.v = v;
    }

    public double generate() {
        return Utils.random(k, v);
    }


}
