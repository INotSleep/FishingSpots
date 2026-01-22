package me.inotsleep.fishingspots.utils;

import com.inotsleep.insutils.api.config.Path;
import com.inotsleep.insutils.spi.config.SerializableObject;

public class Pair extends SerializableObject {
    @Path("min")
    double k;

    @Path("max")
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

    public Pair() {}
}
