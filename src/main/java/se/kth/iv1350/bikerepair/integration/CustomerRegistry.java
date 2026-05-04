package se.kth.iv1350.bikerepair.integration;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains methods that call the database to handle customer information.
 * However, since there is no database customer information is stored here.
 */
  public class CustomerRegistry 
{
    private List<CustomerDTO> customers = new ArrayList<>();
    
    CustomerRegistry()
    {
        addCustomers();
    }
    
    
    /**
     * Searches for the specified customer that has the specified phone number.
     * @param phoneNumber Phone number that identifies the customer.
     * @return Customer's information details.
     */
    public CustomerDTO findCustomer(String phoneNumber)
    {
        for(CustomerDTO customer : customers)
            if(customer.getPhoneNumber().equals(phoneNumber))
                return customer;
        
        return null;
    }
    
    
    
    /**
     * Adds some predefined customers in the registry. 
     */
    private void addCustomers()
    {
        customers.add(new CustomerDTO("John", "john123@gmail.com", "072123456", 
        new BikeDTO("xTreme", "W40", "ABC123")));
        customers.add(new CustomerDTO("Richard", "rd021@gmail.com", "076132418",
        new BikeDTO("eBike", "M3", "XYZ092")));
    }
    
    
    
}
