package se.kth.iv1350.bikerepair.integration;


/**
 * Responsible for creating all the other registries used in the application. 
 *
 */
public class RegistryCreator 
{
    private CustomerRegistry customerRegistry;
    private RepairOrderRegistry repairOrderRegistry;
    
    /**
     * Creates an instance. The other registries are created as well.
     */
    public RegistryCreator()
    {
        this.customerRegistry = new CustomerRegistry();
        this.repairOrderRegistry = new RepairOrderRegistry();
    }
    
    /**
     * Get the value of customerRegistry.
     * 
     * @return The value of CustomerRegistry.
     */
    public CustomerRegistry getCustomerRegistry()
    {
        return customerRegistry;
    }
    
    /**
     * Get the value of repairOrderRegistry.
     * @return The value of repairOrderRegistry.
     */
    public RepairOrderRegistry getRepairOrderRegistry()
    {
        return repairOrderRegistry;
    }
    
}
