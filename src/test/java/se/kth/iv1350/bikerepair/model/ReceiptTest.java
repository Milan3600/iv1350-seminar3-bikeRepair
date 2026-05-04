package se.kth.iv1350.bikerepair.model;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.bikerepair.controller.Controller;
import se.kth.iv1350.bikerepair.integration.Printer;
import se.kth.iv1350.bikerepair.integration.RegistryCreator;


public class ReceiptTest {

    @Test
    public void testCreateReceiptString() 
    {
        Printer printer = new Printer();
        RegistryCreator registryCreator= new RegistryCreator();
        Controller contr = new Controller(registryCreator, printer);
        
        RepairOrderDTO repairOrderDTO = new RepairOrderDTO(
        "The electric motor gave up", "072123456", "XYZ123");
        contr.createRepairOrder(repairOrderDTO);
        RepairOrder storedRepairOrder = contr.findRepairOrderByPhoneNumber("072123456");
        int repairOrderId = storedRepairOrder.getRepairOrderId();
        contr.addDiagnosticResult(repairOrderId, 0, "PASSED");
        contr.addDiagnosticResult(repairOrderId, 1, "PASSED");
        contr.addRepairTask(repairOrderId, "Replace electric motor","The electric"
                + "motor showed no signs of life. A new one was ordered",
                new Amount(350, "EUR"));
        contr.acceptRepairOrder(repairOrderId);
        
        RepairOrder repairOrderToPrint = contr.findRepairOrderByPhoneNumber("072123456");
        Receipt receipt = new Receipt(repairOrderToPrint);
        
        
        String expId = Integer.toString(repairOrderToPrint.getRepairOrderId());
        String expCreateDate = repairOrderToPrint.getDateTimeOfCreation().toString();
        String expProblemDescr = repairOrderToPrint.getProblemDescr();
        String expDiagReport = repairOrderToPrint.getDiagnosticReport().toString();   
        List<RepairTask> expRepairTasks = repairOrderToPrint.getRepairTasks();
        String expStateRepairOrder = repairOrderToPrint.getState().toString();
        String expCompletionDate = repairOrderToPrint.getEstimatedCompletionDate().toString();
        String expTotalCost = repairOrderToPrint.getTotalCost().toString();
        String expBikeBrand = repairOrderToPrint.getCustomerDetails().getBikeDetails().getBrand();
        String expBikeModel = repairOrderToPrint.getCustomerDetails().getBikeDetails().getModel();
        String expBikeSerialNo = repairOrderToPrint.getCustomerDetails().getBikeDetails().
                getSerialNumber();    
        String expCustomerName = repairOrderToPrint.getCustomerDetails().getName();
        String expCustomerEmail = repairOrderToPrint.getCustomerDetails().getEmail();
        String expCustomerPhoneNumber = repairOrderToPrint.getCustomerDetails().getPhoneNumber();
        
        String result = receipt.createReceiptString();
        
        assertTrue(result.contains("Repair order Id: " + expId), "Missing repair order Id");
        assertTrue(result.contains(expCreateDate), "Missing creation date");
        assertTrue(result.contains(expProblemDescr), "Missing problem description");
        assertTrue(result.contains(expDiagReport), "Missing diagnostic report");
        for(RepairTask repTask : expRepairTasks)
            assertTrue(result.contains(repTask.toString()), "Missing repair task");
        assertTrue(result.contains(expStateRepairOrder), "Missing state of repair order");
        assertTrue(result.contains(expCompletionDate), "Missing completion date");
        assertTrue(result.contains("Total cost: " + expTotalCost), "Missing total cost");
        assertTrue(result.contains(expBikeBrand), "Missing bike brand");
        assertTrue(result.contains(expBikeModel), "Missing bike model");
        assertTrue(result.contains(expBikeSerialNo), "Missing bike serial number");
        assertTrue(result.contains("Name: " + expCustomerName), "Missing customer name");
        assertTrue(result.contains(expCustomerEmail), "Missing customer email");
        assertTrue(result.contains(expCustomerPhoneNumber), "Missing customer phone number");
    }
    
}
