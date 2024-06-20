package com.ilp04.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ilp04.entity.Customer;

public class CustomerDAO {
	public Connection getConnection()
	{	
		String connectionURL ="jdbc:mysql://localhost:3306/bankdb?useSSL=false";
		String userName = "root";
		String password = "experion@123";
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(connectionURL,userName,password);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return connection;
		
	}
	public Connection closeConnection(Connection connection)
	{
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	public ArrayList<Customer> getAllCustomers(Connection connection)
	{	ArrayList<Customer> customerList = new ArrayList<Customer>();
		Statement statement;
		ResultSet resultSet;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from customer");
			while(resultSet.next())
			{
				int customerCode = resultSet.getInt(1);
				String customerFirstname = resultSet.getString(2);
			    String customerLastname = resultSet.getString(3);
			    String address = resultSet.getString(4);
			    long phNumber = resultSet.getLong(5);
			    long aadharNo = resultSet.getLong(6);
			    Customer customer = new Customer(customerCode,customerFirstname,customerLastname,address,phNumber, aadharNo);
			    customerList.add(customer);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customerList;
		
	}
	public int insertIntoCustomer(Connection connection, Customer customer) {
		Statement statement = null;
		int rowsAffected=0;
		
		try {
			statement = connection.createStatement();
			String insertQuery = "INSERT INTO customer (customer_code, customer_firstname, customer_lastname, address, phnumber, aadharcard_no) " +
                    "VALUES (" + customer.getCustomerCode() + ", '" + customer.getCustomerFirstname() + "', '" + customer.getCustomerLastname() + "', '" +
                    customer.getAddress() + "', " + customer.getPhNumber() + ", " + customer.getAadharNo() + ")";
			rowsAffected = statement.executeUpdate(insertQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if(statement !=null) {
					statement.close();
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return rowsAffected;
		
	}
}
