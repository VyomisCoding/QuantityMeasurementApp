package org.example.entity;

import java.io.Serializable;

public class QuantityMeasurementEntity implements Serializable {

    private String operation;
    private String result;
    private String error;

    public QuantityMeasurementEntity(String operation, String result) {
        this.operation = operation;
        this.result = result;
    }

    public QuantityMeasurementEntity(String operation, String result, String error) {
        this.operation = operation;
        this.result = result;
        this.error = error;
    }

    public boolean hasError() {
        return error != null;
    }

    public String getOperation() {
        return operation;
    }

    public String getResult() {
        return result;
    }

    public String getError() {
        return error;
    }

    @Override
    public String toString(){
        return operation+" "+result;
    }
}