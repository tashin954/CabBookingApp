package com.cba.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cba.entities.Customer;
import com.cba.exceptions.CustomerIdNotFoundException;
import com.cba.exceptions.CustomerNotFoundInDatabaseException;
import com.cba.exceptions.CustomersNotFoundException;
import com.cba.exceptions.DriverIdNotFoundException;
import com.cba.repositories.ICustomerRepository;
import com.cba.repositories.IDriverRepository;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private ICustomerRepository customerRepository;

	@Autowired
	private IDriverRepository driverRepository;

	@Override
	public Customer insertCustomer(Customer customer) {

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		customer.setPassword(passwordEncoder.encode(customer.getPassword()));

		return customerRepository.save(customer);

	}

	@Override
	public Customer updateCustomer(Customer customer) {
		Optional<Customer> data = customerRepository.findById(customer.getUserId());
		if (!(data.isPresent()))
			throw new CustomerIdNotFoundException("Customer not found for ID: " + customer.getUserId());

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		data.get().setUsername(customer.getUsername());
		data.get().setPassword(passwordEncoder.encode(customer.getPassword()));
		data.get().setAddress(customer.getAddress());
		data.get().setMobileNumber(customer.getMobileNumber());
		data.get().setEmail(customer.getEmail());
		return customerRepository.save(data.get());

	}

	@Override
	public Customer deleteCustomer(int customerId) {
		Customer data = customerRepository.findById(customerId)
				.orElseThrow(() -> new CustomerIdNotFoundException("Customer not found for ID: " + customerId));
		customerRepository.delete(data);
		return data;

	}

	@Override
	public List<Customer> viewCustomers() {
		List<Customer> list = customerRepository.findAll();
		if (list.isEmpty()) {
			throw new CustomersNotFoundException("No Customers Found");
		}
		return list;
	}

	@Override
	public Customer viewCustomer(int customerId) {

		Optional<Customer> list = customerRepository.findById(customerId);
		if (!(list.isPresent())) {
			throw new CustomersNotFoundException("Customer not found for ID: " + customerId);
		}
		return list.get();
	}

	@Override
	public Customer validateCustomer(String userName, String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		Optional<Customer> customer = customerRepository.findByUsername(userName);
		if ((customer.isPresent()) && (passwordEncoder.matches(password, customer.get().getPassword()))) {
			return customer.get();
		} else {
			throw new CustomerNotFoundInDatabaseException("Invalid Username/ Password");
		}
	}

	public String rateDriver(int driverId, float driverRating) {
		Optional<com.cba.entities.Driver> optionalDriver = driverRepository.findById(driverId);
		if (optionalDriver.isPresent()) {
			optionalDriver.get().setRating(driverRating);
			driverRepository.save(optionalDriver.get());
			return "Driver with Id " + driverId + " rated with " + driverRating;
		} else {
			throw new DriverIdNotFoundException("No Driver found with Id: " + driverId);
		}
	}
}
