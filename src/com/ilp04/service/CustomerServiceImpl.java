package com.ilp04.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ilp04.dao.CustomerDAO;
import com.ilp04.entity.Customer;

public class CustomerServiceImpl implements CustomerService {

	
	public ArrayList<Customer> getAllCustomers() {
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		CustomerDAO customerDAO = new CustomerDAO();
		Connection connection = customerDAO.getConnection();
		customerList = customerDAO.getAllCustomers(connection);
		return customerList;
	}

	
	public int insertIntoCustomer(Customer customer) {
		CustomerDAO customerDAO = new CustomerDAO();
		Connection connection = customerDAO.getConnection();
		int rowsAffected = customerDAO.insertIntoCustomer(connection,customer);
		return rowsAffected;
	}

	
	public int updateCustomer(Customer customer) {
		CustomerDAO customerDAO = new CustomerDAO();
		Connection connection = null;
	    int rowsAffected = 0;
	    PreparedStatement preparedStatement = null;

	    try {
	        connection = customerDAO.getConnection();
	        String updateQuery = "UPDATE customer SET customer_firstname = ?, customer_lastname = ?, address = ?, phnumber = ?, aadharcard_no = ? WHERE customer_code = ?";
	        preparedStatement = connection.prepareStatement(updateQuery);
	        preparedStatement.setString(1, customer.getCustomerFirstname());
	        preparedStatement.setString(2, customer.getCustomerLastname());
	        preparedStatement.setString(3, customer.getAddress());
	        preparedStatement.setLong(4, customer.getPhNumber());
	        preparedStatement.setLong(5, customer.getAadharNo());
	        preparedStatement.setInt(6, customer.getCustomerCode());
	        
	        rowsAffected = preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (preparedStatement != null) {
	            try {
	                preparedStatement.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (connection != null) {
	            customerDAO.closeConnection(connection);
	        }
	    }
	    return rowsAffected;
	}	
	

}
