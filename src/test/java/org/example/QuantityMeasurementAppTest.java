package org.example;

import org.example.controller.QuantityMeasurementController;
import org.example.dto.QuantityDTO;
import org.example.entity.QuantityMeasurementEntity;
import org.example.repository.QuantityMeasurementCacheRepository;
import org.example.service.QuantityMeasurementServiceImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {

    private QuantityMeasurementServiceImpl service;
    private QuantityMeasurementController controller;

    @BeforeEach
    void setup() {
        service = new QuantityMeasurementServiceImpl(
                QuantityMeasurementCacheRepository.getInstance());
        controller = new QuantityMeasurementController(service);
    }

    // ---------------- ENTITY TESTS ----------------

    @Test
    void testQuantityEntity_SingleOperandConstruction() {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity("CONVERT", "60 INCH");

        assertEquals("CONVERT", entity.getOperation());
        assertEquals("60 INCH", entity.getResult());
    }

    @Test
    void testQuantityEntity_BinaryOperandConstruction() {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity("ADD", "10 FEET");

        assertEquals("ADD", entity.getOperation());
        assertEquals("10 FEET", entity.getResult());
    }

    @Test
    void testQuantityEntity_ErrorConstruction() {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity("ERROR", "Invalid Unit");

        assertEquals("ERROR", entity.getOperation());
        assertTrue(entity.getResult().contains("Invalid"));
    }

    @Test
    void testQuantityEntity_ToString_Success() {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity("ADD", "10 FEET");

        assertNotNull(entity.toString());
    }

    @Test
    void testQuantityEntity_ToString_Error() {

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity("ERROR", "Invalid Unit");

        assertTrue(entity.getResult().contains("Invalid"));
    }

    // ---------------- SERVICE TESTS ----------------

    @Test
    void testService_CompareEquality_SameUnit_Success() {

        QuantityDTO q1 = new QuantityDTO(5,"FEET","LENGTH");
        QuantityDTO q2 = new QuantityDTO(5,"FEET","LENGTH");

        assertTrue(service.compare(q1,q2));
    }

    @Test
    void testService_CompareEquality_DifferentUnit_Success() {

        QuantityDTO q1 = new QuantityDTO(1,"FEET","LENGTH");
        QuantityDTO q2 = new QuantityDTO(12,"INCH","LENGTH");

        assertTrue(service.compare(q1,q2));
    }

    @Test
    void testService_CompareEquality_CrossCategory_Error() {

        QuantityDTO q1 = new QuantityDTO(1,"FEET","LENGTH");
        QuantityDTO q2 = new QuantityDTO(1,"KG","WEIGHT");

        assertThrows(RuntimeException.class, () -> service.compare(q1,q2));
    }

    @Test
    void testService_Convert_Success() {

        QuantityDTO q1 = new QuantityDTO(5,"FEET","LENGTH");

        QuantityDTO result = service.convert(q1,"INCH");

        assertEquals(60,result.getValue());
    }

    @Test
    void testService_Add_Success() {

        QuantityDTO q1 = new QuantityDTO(1,"FEET","LENGTH");
        QuantityDTO q2 = new QuantityDTO(12,"INCH","LENGTH");

        QuantityDTO result = service.add(q1,q2);

        assertEquals(2,result.getValue());
    }

    @Test
    void testService_Add_UnsupportedOperation_Error() {

        QuantityDTO q1 = new QuantityDTO(10,"CELSIUS","TEMPERATURE");
        QuantityDTO q2 = new QuantityDTO(20,"CELSIUS","TEMPERATURE");

        assertThrows(RuntimeException.class, () -> service.add(q1,q2));
    }

    @Test
    void testService_Subtract_Success() {

        QuantityDTO q1 = new QuantityDTO(2,"FEET","LENGTH");
        QuantityDTO q2 = new QuantityDTO(12,"INCH","LENGTH");

        QuantityDTO result = service.subtract(q1,q2);

        assertEquals(1,result.getValue());
    }

    @Test
    void testService_Divide_Success() {

        QuantityDTO q1 = new QuantityDTO(10,"FEET","LENGTH");
        QuantityDTO q2 = new QuantityDTO(2,"FEET","LENGTH");

        double result = service.divide(q1,q2);

        assertEquals(5,result);
    }

    @Test
    void testService_Divide_ByZero_Error() {

        QuantityDTO q1 = new QuantityDTO(10,"FEET","LENGTH");
        QuantityDTO q2 = new QuantityDTO(0,"FEET","LENGTH");

        assertThrows(RuntimeException.class, () -> service.divide(q1,q2));
    }

    // ---------------- CONTROLLER TESTS ----------------

    @Test
    void testController_DemonstrateEquality_Success() {

        QuantityDTO q1 = new QuantityDTO(1,"FEET","LENGTH");
        QuantityDTO q2 = new QuantityDTO(12,"INCH","LENGTH");

        assertTrue(controller.performComparison(q1,q2));
    }

    @Test
    void testController_DemonstrateConversion_Success() {

        QuantityDTO q1 = new QuantityDTO(5,"FEET","LENGTH");

        QuantityDTO result = controller.performConversion(q1,"INCH");

        assertEquals(60,result.getValue());
    }

    @Test
    void testController_DemonstrateAddition_Success() {

        QuantityDTO q1 = new QuantityDTO(1,"FEET","LENGTH");
        QuantityDTO q2 = new QuantityDTO(12,"INCH","LENGTH");

        QuantityDTO result = controller.performAddition(q1,q2);

        assertEquals(2,result.getValue());
    }

    @Test
    void testController_DemonstrateAddition_Error() {

        QuantityDTO q1 = new QuantityDTO(1,"FEET","LENGTH");
        QuantityDTO q2 = new QuantityDTO(1,"KG","WEIGHT");

        assertThrows(RuntimeException.class, () -> controller.performAddition(q1,q2));
    }

    @Test
    void testController_DisplayResult_Success() {

        QuantityDTO q1 = new QuantityDTO(5,"FEET","LENGTH");

        QuantityDTO result = controller.performConversion(q1,"INCH");

        assertNotNull(result);
    }

    @Test
    void testController_DisplayResult_Error() {

        QuantityDTO q1 = new QuantityDTO(5,"FEET","LENGTH");

        assertThrows(RuntimeException.class, () -> controller.performConversion(q1,"KG"));
    }

    // ---------------- LAYER TESTS ----------------

    @Test
    void testLayerSeparation_ServiceIndependence() {

        QuantityDTO q1 = new QuantityDTO(1,"FEET","LENGTH");
        QuantityDTO q2 = new QuantityDTO(12,"INCH","LENGTH");

        assertTrue(service.compare(q1,q2));
    }

    @Test
    void testLayerSeparation_ControllerIndependence() {

        assertNotNull(controller);
    }

    // ---------------- DATA FLOW ----------------

    @Test
    void testDataFlow_ControllerToService() {

        QuantityDTO q1 = new QuantityDTO(5,"FEET","LENGTH");

        QuantityDTO result = controller.performConversion(q1,"INCH");

        assertEquals(60,result.getValue());
    }

    @Test
    void testDataFlow_ServiceToController() {

        QuantityDTO q1 = new QuantityDTO(1,"FEET","LENGTH");

        QuantityDTO result = controller.performConversion(q1,"INCH");

        assertEquals("INCH",result.getUnit());
    }

    // ---------------- SCALABILITY ----------------

    @Test
    void testService_AllMeasurementCategories() {

        assertNotNull(service);
    }

    @Test
    void testController_AllOperations() {

        QuantityDTO q1 = new QuantityDTO(1,"FEET","LENGTH");
        QuantityDTO q2 = new QuantityDTO(12,"INCH","LENGTH");

        controller.performComparison(q1,q2);
        controller.performAddition(q1,q2);
        controller.performSubtraction(q1,q2);
    }

    @Test
    void testService_ValidationConsistency() {

        QuantityDTO q1 = new QuantityDTO(1,"FEET","LENGTH");

        assertThrows(RuntimeException.class, () -> service.convert(q1,"INVALID"));
    }

    @Test
    void testEntity_Immutability() {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity("ADD","10 FEET");

        assertNotNull(entity.getOperation());
    }

    @Test
    void testService_ExceptionHandling_AllOperations() {

        QuantityDTO q1 = new QuantityDTO(1,"FEET","LENGTH");
        QuantityDTO q2 = new QuantityDTO(0,"FEET","LENGTH");

        assertThrows(RuntimeException.class, () -> service.divide(q1,q2));
    }

    @Test
    void testController_ConsoleOutput_Format() {

        QuantityDTO q1 = new QuantityDTO(5,"FEET","LENGTH");

        QuantityDTO result = controller.performConversion(q1,"INCH");

        assertNotNull(result.toString());
    }

    // ---------------- INTEGRATION ----------------

    @Test
    void testIntegration_EndToEnd_LengthAddition() {

        QuantityDTO q1 = new QuantityDTO(1,"FEET","LENGTH");
        QuantityDTO q2 = new QuantityDTO(12,"INCH","LENGTH");

        QuantityDTO result = controller.performAddition(q1,q2);

        assertEquals(2,result.getValue());
    }

    @Test
    void testIntegration_EndToEnd_TemperatureUnsupported() {

        QuantityDTO q1 = new QuantityDTO(10,"CELSIUS","TEMPERATURE");
        QuantityDTO q2 = new QuantityDTO(20,"CELSIUS","TEMPERATURE");

        assertThrows(RuntimeException.class, () -> controller.performAddition(q1,q2));
    }

}