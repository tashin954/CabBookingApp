package com.cba.services;

import java.util.List;

import com.cba.entities.Customer;

public interface ICustomerService {
	public Customer insertCustomer(Customer customer);

	public Customer updateCustomer(Customer customer);

	public Customer deleteCustomer(int customerId);

	public List<Customer> viewCustomers();

	public Customer viewCustomer(int customerId);
	
	public Customer validateCustomer(String userName, String password);
}
