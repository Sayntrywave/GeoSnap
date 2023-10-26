package com.korotkov.hackathon.util;

public class Latitude {

    public enum LatitudeType{
        N,
        S
    }

    private LatitudeType type;
    private double degree, minute, second;

    public Latitude(LatitudeType type, double degree, double minute, double second) {
        this.type = type;
        this.degree = degree;
        this.minute = minute;
        this.second = second;
    }

    public LatitudeType getType() {
        return type;
    }

    public double getDegree() {
        return degree;
    }

    public double getMinute() {
        return minute;
    }

    public double getSecond() {
        return second;
    }
}
