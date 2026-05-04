package se.kth.iv1350.bikerepair.controller;

import java.util.List;
import se.kth.iv1350.bikerepair.integration.CustomerDTO;
import se.kth.iv1350.bikerepair.integration.RegistryCreator;
import se.kth.iv1350.bikerepair.integration.CustomerRegistry;
import se.kth.iv1350.bikerepair.integration.Printer;
import se.kth.iv1350.bikerepair.integration.RepairOrderRegistry;
import se.kth.iv1350.bikerepair.model.Amount;
import se.kth.iv1350.bikerepair.model.Receipt;
import se.kth.iv1350.bikerepair.model.RepairOrder;
import se.kth.iv1350.bikerepair.model.RepairOrderDTO;
import se.kth.iv1350.bikerepair.model.RepairTask;

/**
 * This is the application's only Controller. It forwards method calls 
 * to classes in lower layers.
 */
public class Controller 
{
    private RegistryCreator regCreator;
    private CustomerRegistry customerRegistry;
    private RepairOrderRegistry repairOrderRegistry;
    private Printer printer;
    
    /**
     * Creates an instance.
     * @param regCreator RegistryCreator creates all the other
     * registries. Used to get the created registries.
     * @param printer The printer that is called to print out the receipt
     * to <code>System.out</code>.
     */
    public Controller(RegistryCreator regCreator, Printer printer)
    {
        this.regCreator = regCreator;
        this.customerRegistry = regCreator.getCustomerRegistry();
        this.repairOrderRegistry = regCreator.getRepairOrderRegistry();
        this.printer = printer;
    }
    
    /**
     * Calls the corresponding method in CustomerRegistry
     * to retrieve the customer's information.
     * @param phoneNum Customer's phone number.
     * @return Customer's information.
     */
    public CustomerDTO findCustomer(String phoneNum)
    {
        CustomerDTO foundCustomer = customerRegistry.findCustomer(phoneNum);
        return foundCustomer;
    }
    
    /**
     * Calls the corresponding method in RepairOrderRegistry to save
     * the newly created repair order.
     * @param repairOrderDTO The object that is used to 
     * create the repair order.
     */
    public void createRepairOrder(RepairOrderDTO repairOrderDTO)
    {
        CustomerDTO customerDTO = customerRegistry.findCustomer
        (repairOrderDTO.getCustomerPhoneNumber());
        RepairOrder repairOrder = new RepairOrder(repairOrderDTO,
        customerDTO);
        repairOrderRegistry.createRepairOrder(repairOrder);
    }
    
    /**
     * Returns all the stored repair orders.
     * @return All repair orders in a list.
     */
    public List<RepairOrder> findAllRepairOrders()
    {
        return repairOrderRegistry.findAllRepairOrders();
    }
    
    /**
     * Finds the repair order that has the specified phone number.
     * @param phoneNumber The phone number used to find the right repair order.
     * @return Repair order matching the phone number.
     */
    public RepairOrder findRepairOrderByPhoneNumber(String phoneNumber)
    {
        return repairOrderRegistry.findRepairOrderByPhoneNumber(phoneNumber);
    }
    
    /**
     * Adds the result to a specific diagnostic task in a repair order.
     * @param repairOrderId The repair order to add the diagnostic task result to.
     * @param diagTaskIndex Specific diagnostic task to add the result to.
     * @param result The result to add.
     */
    public void addDiagnosticResult(int repairOrderId, int diagTaskIndex, String result)
    {
        RepairOrder repairOrder = repairOrderRegistry.findRepairOrderById(repairOrderId);
        repairOrder.addDiagnosticResult(diagTaskIndex, result);
        updateRepairOrder(repairOrder);
    }
    
    /**
     * Adds the repair task to a specific repair order.
     * @param repairOrderId The specific repair order where the repair task shall be added.
     * @param name Name of the repair task.
     * @param description Description of the repair task.
     * @param amount Cost of the repair task.
     */
    public void addRepairTask(int repairOrderId, String name, String description, Amount amount)
    {
        RepairOrder repairOrder = repairOrderRegistry.findRepairOrderById(repairOrderId);
        RepairTask repairTask = new RepairTask(name, description, amount);
        repairOrder.addRepairTask(repairTask);
        updateRepairOrder(repairOrder);
    }
    
    
    /**
     * Registers that repair order has been accepted, saves it, and prints a receipt
     * of the entire repair order.
     * @param repairOrderId The repair order to mark as accepted.
     */
    public void acceptRepairOrder(int repairOrderId)
    {
        RepairOrder repairOrder = repairOrderRegistry.findRepairOrderById(repairOrderId);
        repairOrder.accept();
        updateRepairOrder(repairOrder);
        Receipt receipt = new Receipt(repairOrder);
        printer.printReceipt(receipt);
    }
    
    private void updateRepairOrder(RepairOrder repairOrder)
    {
        repairOrderRegistry.updateRepairOrder(repairOrder);
    }
    
}
