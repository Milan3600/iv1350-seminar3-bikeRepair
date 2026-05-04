package se.kth.iv1350.bikerepair.startup;

import se.kth.iv1350.bikerepair.controller.Controller;
import se.kth.iv1350.bikerepair.integration.Printer;
import se.kth.iv1350.bikerepair.integration.RegistryCreator;
import se.kth.iv1350.bikerepair.view.View;

/**
 * Initiates the startup of the application. Runs all the system operations
 * in the static method main.
 */
public class Main 
{
   
  /**
   * Starts the application.
   * @param args No argument is needed for the application.
   */
  public static void main(String[] args)
  {
      RegistryCreator regCreator = new RegistryCreator();
      Printer printer = new Printer();
      Controller contr = new Controller(regCreator, printer);
      View view = new View(contr);
      
      view.exampleAllSystemOperations();
  }
}