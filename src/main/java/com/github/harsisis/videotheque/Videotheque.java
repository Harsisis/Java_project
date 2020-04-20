package com.github.harsisis.videotheque;

public abstract class Videotheque {

    private double reducFidele;

    public abstract boolean isFidele();

    public double getReduction () {
        if (isFidele())
            this.reducFidele = 0.1;
        else
            this.reducFidele = 0;
        return this.reducFidele;
    }
}
