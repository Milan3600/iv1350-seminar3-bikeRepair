package se.kth.iv1350.bikerepair.view;

import java.util.List;
import se.kth.iv1350.bikerepair.controller.Controller;
import se.kth.iv1350.bikerepair.integration.CustomerDTO;
import se.kth.iv1350.bikerepair.model.Amount;
import se.kth.iv1350.bikerepair.model.RepairOrder;
import se.kth.iv1350.bikerepair.model.RepairOrderDTO;

/**
 * This class represents the entire View.
 *
 */
public class View 
{
    private Controller contr;
    
    
    /**
     * Creates a new instance.
     * @param contr Controller that is used for all system operations.
     */
    public View(Controller contr) 
    {
        this.contr = contr;
    }
    
    
    /**
     * Represents different user inputs to the user interface. It triggers all
     * system operations to execute in sequential order.
     */
    public void exampleAllSystemOperations()
    {
        String customerPhoneNumber = "072123456";
        CustomerDTO foundCustomer = contr.findCustomer(customerPhoneNumber);
        System.out.println("Result of searching for customer " + foundCustomer);
        
        RepairOrderDTO repairOrderDTO = new RepairOrderDTO("Faulty gear shifter",
                "072123456", "ABC123");
        contr.createRepairOrder(repairOrderDTO);
        System.out.println("The repair order has been created and saved to the database");
        
        List<RepairOrder> allRepairOrders = contr.findAllRepairOrders();
        System.out.println("Result of retrieving all repair orders below ");
        for(RepairOrder repairOrder : allRepairOrders)
        {
            System.out.println(repairOrder);
        }
        
        contr.addDiagnosticResult(0, 0, "PASSED");
        contr.addDiagnosticResult(0, 1, "PASSED");
        System.out.println("The diagnostic result was added and "
                + "the repair order has been updated");
        
        contr.addRepairTask(0, "Fix gear shifter", 
        "Replace worn components and adjust gear indexing", new Amount(28, "EUR"));
        System.out.println("The repair task was added to the repair order");
        
        RepairOrder repairOrder = contr.findRepairOrderByPhoneNumber("072123456");
        System.out.println("Found repair order with specified phone number " + repairOrder);
        
        System.out.println("The repair order has been accepted and will be printed shortly.");
        System.out.println("------------- Receipt -------------");
        contr.acceptRepairOrder(0);
        System.out.println("---------- End of receipt ----------");
    }
    
}