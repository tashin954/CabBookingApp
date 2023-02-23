package com.cba.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cba.entities.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
	public Optional<Customer> findByUsername(String username);
}
