package com.app.quantityMeasurementApp.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class QuantityDTO {

    @NotNull(message = "Value must not be null")
    private Double value;

    @NotBlank(message = "Unit must not be blank")
    private String unit;

    public QuantityDTO() {}

    public QuantityDTO(Double value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}