package org.example.service;

import org.example.dto.QuantityDTO;
import org.example.entity.QuantityMeasurementEntity;
import org.example.exception.QuantityMeasurementException;
import org.example.model.Quantity;
import org.example.repository.IQuantityMeasurementRepository;
import org.example.units.*;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    private static final double EPSILON = 0.001;
    private IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    // ---------------- DTO -> Quantity ----------------

    private Quantity<IMeasurable> convertDTOToQuantity(QuantityDTO dto){

        String unitName = dto.getUnit().toUpperCase();
        double value = dto.getValue();

        switch (dto.getMeasurementType().toUpperCase()) {

            case "LENGTH":
                return new Quantity<>(value, LengthUnit.valueOf(unitName));

            case "WEIGHT":
                return new Quantity<>(value, WeightUnit.valueOf(unitName));

            case "VOLUME":
                return new Quantity<>(value, VolumeUnit.valueOf(unitName));

            case "TEMPERATURE":
                return new Quantity<>(value, TemperatureUnit.valueOf(unitName));

            default:
                throw new QuantityMeasurementException("Invalid measurement type");
        }
    }

    // ---------------- Quantity -> DTO ----------------

    private QuantityDTO convertQuantityToDTO(Quantity<IMeasurable> quantity, String measurementType){

        return new QuantityDTO(
                quantity.getValue(),
                quantity.getUnit().toString(),
                measurementType
        );
    }

    // ---------------- Compare ----------------

    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {

        if(!q1.getMeasurementType().equalsIgnoreCase(q2.getMeasurementType())){
            throw new QuantityMeasurementException("Different measurement types");
        }

        Quantity<IMeasurable> quantity1 = convertDTOToQuantity(q1);
        Quantity<IMeasurable> quantity2 = convertDTOToQuantity(q2);

        double base1 = quantity1.getUnit().toBaseUnit(quantity1.getValue());
        double base2 = quantity2.getUnit().toBaseUnit(quantity2.getValue());

        boolean result = Math.abs(base1 - base2) < EPSILON;

        repository.save(new QuantityMeasurementEntity(
                "COMPARE",
                q1 + " & " + q2 + " = " + result
        ));

        return result;
    }

    // ---------------- Convert ----------------

    @Override
    public QuantityDTO convert(QuantityDTO dto, String targetUnit) {

        Quantity<IMeasurable> inputQuantity = convertDTOToQuantity(dto);

        IMeasurable target;

        switch (dto.getMeasurementType().toUpperCase()) {

            case "LENGTH":
                target = LengthUnit.valueOf(targetUnit.toUpperCase());
                break;

            case "WEIGHT":
                target = WeightUnit.valueOf(targetUnit.toUpperCase());
                break;

            case "VOLUME":
                target = VolumeUnit.valueOf(targetUnit.toUpperCase());
                break;

            case "TEMPERATURE":
                target = TemperatureUnit.valueOf(targetUnit.toUpperCase());
                break;

            default:
                throw new QuantityMeasurementException("Invalid measurement type");
        }

        double baseValue = inputQuantity.getUnit().toBaseUnit(inputQuantity.getValue());

        double convertedValue = target.fromBaseUnit(baseValue);

        Quantity<IMeasurable> output = new Quantity<>(convertedValue, target);

        QuantityDTO result = convertQuantityToDTO(output, dto.getMeasurementType());

        repository.save(new QuantityMeasurementEntity(
                "CONVERT",
                dto + " -> " + result
        ));

        return result;
    }

    // ---------------- Add ----------------

    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {

        if(!q1.getMeasurementType().equalsIgnoreCase(q2.getMeasurementType())){
            throw new QuantityMeasurementException("Different measurement types");
        }

        if(q1.getMeasurementType().equalsIgnoreCase("TEMPERATURE")){
            throw new QuantityMeasurementException("Temperature addition not supported");
        }

        Quantity<IMeasurable> quantity1 = convertDTOToQuantity(q1);
        Quantity<IMeasurable> quantity2 = convertDTOToQuantity(q2);

        double base1 = quantity1.getUnit().toBaseUnit(quantity1.getValue());
        double base2 = quantity2.getUnit().toBaseUnit(quantity2.getValue());

        double resultBase = base1 + base2;

        double resultValue = quantity1.getUnit().fromBaseUnit(resultBase);

        return new QuantityDTO(resultValue, q1.getUnit(), q1.getMeasurementType());
    }

    // ---------------- Subtract ----------------

    @Override
    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {

        if(!q1.getMeasurementType().equalsIgnoreCase(q2.getMeasurementType())){
            throw new QuantityMeasurementException("Different measurement types");
        }

        Quantity<IMeasurable> quantity1 = convertDTOToQuantity(q1);
        Quantity<IMeasurable> quantity2 = convertDTOToQuantity(q2);

        double base1 = quantity1.getUnit().toBaseUnit(quantity1.getValue());
        double base2 = quantity2.getUnit().toBaseUnit(quantity2.getValue());

        double resultBase = base1 - base2;

        double resultValue = quantity1.getUnit().fromBaseUnit(resultBase);

        Quantity<IMeasurable> resultQuantity =
                new Quantity<>(resultValue, quantity1.getUnit());

        QuantityDTO result = convertQuantityToDTO(resultQuantity,q1.getMeasurementType());

        repository.save(new QuantityMeasurementEntity(
                "SUBTRACT",
                q1 + " - " + q2 + " = " + result
        ));

        return result;
    }

    // ---------------- Divide ----------------

    @Override
    public double divide(QuantityDTO q1, QuantityDTO q2) {

        if(q2.getValue() == 0){
            throw new QuantityMeasurementException("Division by zero");
        }

        double result = q1.getValue() / q2.getValue();

        repository.save(new QuantityMeasurementEntity(
                "DIVIDE",
                q1 + " / " + q2 + " = " + result
        ));

        return result;
    }
}