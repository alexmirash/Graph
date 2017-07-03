package com.alex.mirash.graph.graph.model;

/**
 * @author Mirash
 */

public class ValueModel {
    private float time;
    private float value;

    public ValueModel(float time, float value) {
        this.time = time;
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }
}
