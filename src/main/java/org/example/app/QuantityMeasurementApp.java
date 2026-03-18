package org.example.app;

import org.example.controller.QuantityMeasurementController;
import org.example.dto.QuantityDTO;
import org.example.repository.IQuantityMeasurementRepository;
import org.example.repository.QuantityMeasurementCacheRepository;
import org.example.repository.QuantityMeasurementDatabaseRepository;
import org.example.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        IQuantityMeasurementRepository repository = new QuantityMeasurementDatabaseRepository();

        QuantityMeasurementServiceImpl service =
                new QuantityMeasurementServiceImpl(repository);

        QuantityMeasurementController controller =
                new QuantityMeasurementController(service);

        QuantityDTO q1 = new QuantityDTO(1,"FEET","LENGTH");
        QuantityDTO q2 = new QuantityDTO(12,"INCH","LENGTH");

        System.out.println(controller.performComparison(q1,q2));

        System.out.println(controller.performAddition(q1,q2));

        System.out.println(controller.performSubtraction(q1,q2));

        System.out.println(controller.performConversion(q1,"INCH"));

        System.out.println(controller.performDivision(q1,new QuantityDTO(1,"FEET","LENGTH")));

        System.out.println(repository.findAll());
    }
}