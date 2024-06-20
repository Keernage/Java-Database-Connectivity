package com.ilp04.utility;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp04.entity.Customer;
import com.ilp04.service.CustomerService;
import com.ilp04.service.CustomerServiceImpl;

public class CustomerUtility {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        char goToMainMenu;
        do {
            System.out.println("1.Display All Customers, 2.Insert, 3.Update");
            int mainChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over
            switch (mainChoice) {
                case 1:
                    getAllCustomers();
                    break;
                case 2:
                    insertIntoCustomer();
                    break;
                case 3:
                    updateCustomer();
                    break;
                default:
                    System.out.println("Invalid Input");
            }
            System.out.println("Go to main menu (y/n)");
            goToMainMenu = scanner.next().charAt(0);
            scanner.nextLine(); // Consume newline left-over
        } while (goToMainMenu == 'y');
    }

    private static void getAllCustomers() {
        CustomerService customerService = new CustomerServiceImpl();
        ArrayList<Customer> customerList = customerService.getAllCustomers();
        for (Customer customer : customerList) {
            System.out.println(customer.getCustomerCode() + "   " +
                               customer.getCustomerFirstname() + "   " +
                               customer.getCustomerLastname() + "   " +
                               customer.getAddress() + "   " +
                               customer.getPhNumber() + "   " +
                               customer.getAadharNo());
        }
    }

    private static void insertIntoCustomer() {
        CustomerService customerService = new CustomerServiceImpl();
        System.out.println("Enter Customer First Name");
        String customerFirstName = scanner.nextLine();
        System.out.println("Enter Customer Last Name");
        String customerLastName = scanner.nextLine();
        System.out.println("Enter Customer Address");
        String address = scanner.nextLine();
        System.out.println("Enter Customer Phone Number");
        long phNumber = scanner.nextLong();
        System.out.println("Enter Customer Aadhar Number");
        long aadharNo = scanner.nextLong();
        scanner.nextLine(); // Consume newline left-over

        // Generate customerCode (auto-increment logic could be implemented here)
        int customerCode = generateCustomerCode();

        Customer customer = new Customer(customerCode, customerFirstName, customerLastName, address, phNumber, aadharNo);
        int rowsInserted = customerService.insertIntoCustomer(customer);
        if (rowsInserted > 0) {
            System.out.println("Customer inserted successfully! Rows affected: " + rowsInserted);
        } else {
            System.out.println("Failed to insert Customer");
        }
    }

    private static void updateCustomer() {
        CustomerService customerService = new CustomerServiceImpl();
        System.out.println("Enter Customer Code of the customer to update");
        int customerCode = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        System.out.println("Enter new Customer First Name");
        String customerFirstName = scanner.nextLine();
        System.out.println("Enter new Customer Last Name");
        String customerLastName = scanner.nextLine();
        System.out.println("Enter new Customer Address");
        String address = scanner.nextLine();
        System.out.println("Enter new Customer Phone Number");
        long phNumber = scanner.nextLong();
        System.out.println("Enter new Customer Aadhar Number");
        long aadharNo = scanner.nextLong();
        scanner.nextLine(); 

        Customer customer = new Customer(customerCode, customerFirstName, customerLastName, address, phNumber, aadharNo);
        int rowsUpdated = customerService.updateCustomer(customer);
        if (rowsUpdated > 0) {
            System.out.println("Customer updated successfully! Rows affected: " + rowsUpdated);
        } else {
            System.out.println("Failed to update Customer");
        }
    }

   private static int generateCustomerCode() {
       
       return 6; 
    }
}
