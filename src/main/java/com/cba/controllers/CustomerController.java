package com.cba.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cba.entities.Customer;
import com.cba.helper.validateUser;
import com.cba.responses.ErrorInfo;
import com.cba.services.CustomerServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class CustomerController {
	@Autowired
	private CustomerServiceImpl customerService;

	@Operation(summary = "Adding Customer with their Trips in the REST API DB")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = Customer.class))),
			@ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = ErrorInfo.class))) })

	@PostMapping("/customers")
	public Customer insertCustomer(@Valid @RequestBody Customer customer) {
		return customerService.insertCustomer(customer);
	}

	@Operation(summary = "Updating Customer in the REST API DB")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = Customer.class))),
			@ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = ErrorInfo.class))) })

	@PutMapping("/customers")
	public Customer updateCustomer(@Valid @RequestBody Customer customer) {
		return customerService.updateCustomer(customer);
	}

	@Operation(summary = "Delete the Customer on the basis of Customer Id from REST API DB")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = Customer.class))),
			@ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = ErrorInfo.class))) })

	@DeleteMapping("/customers/{id}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable int id) {
		Customer tr = customerService.deleteCustomer(id);
		return new ResponseEntity<>(tr, HttpStatus.OK);
	}

	@Operation(summary = "Fetching Customer Id from the REST API DB on the basis of Customer Id from REST API DB")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = Customer.class))),
			@ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = ErrorInfo.class))) })

	@GetMapping("/customers/{id}")
	public ResponseEntity<Customer> viewcustomer(@PathVariable int id) {
		return new ResponseEntity<>(customerService.viewCustomer(id), HttpStatus.OK);
	}

	@Operation(summary = "fetch all customers in the REST API DB")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = Customer.class))),
			@ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = ErrorInfo.class))) })

	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> viewCustomers() {
		List<Customer> tr = customerService.viewCustomers();
		return new ResponseEntity<>(tr, HttpStatus.OK);

	}

	@Operation(summary = "Validate customers from the REST API DB")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = Customer.class))),
			@ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = ErrorInfo.class))) })

	@PostMapping("/customers/validate")
	public ResponseEntity<Customer> validate(@RequestBody validateUser vUser) {
		return new ResponseEntity<>(customerService.validateCustomer(vUser.getUserName(), vUser.getPassword()),
				HttpStatus.OK);

	}

	@Operation(summary = "Validate customers from the REST API DB")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "text/plain", schema = @Schema(oneOf = Customer.class))),
			@ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(mediaType = "text/plain", schema = @Schema(oneOf = ErrorInfo.class))) })

	@GetMapping("/customers/rateDriver/{driverId}/{rating}")
	public ResponseEntity<String> rating(@PathVariable("driverId") int driverId, @PathVariable("rating") float rating) {
		return new ResponseEntity<>(customerService.rateDriver(driverId, rating), HttpStatus.OK);

	}

}
