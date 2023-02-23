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

import com.cba.entities.Driver;
import com.cba.entities.TripBooking;
import com.cba.responses.ErrorInfo;
import com.cba.services.DriverServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
// @Validated
public class DriverController {

	@Autowired
	private DriverServiceImpl driverSer;

	@Operation(summary = "Adding Drivers in the REST API DB")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = TripBooking.class))),
			@ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = ErrorInfo.class))) })
	@PostMapping(value = "/drivers")
	public ResponseEntity<Driver> insertDriver(@RequestBody @Valid Driver d) {
		return new ResponseEntity<>(driverSer.insertDriver(d), HttpStatus.OK);
	}

	@Operation(summary = "Updating Drivers in the REST API DB")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = TripBooking.class))),
			@ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = ErrorInfo.class))) })
	@PutMapping(value = "/drivers")
	public ResponseEntity<Driver> updateDriver(@Valid @RequestBody Driver d) {
		return new ResponseEntity<>(driverSer.updateDriver(d), HttpStatus.OK);
	}

	@Operation(summary = "Deleting Driver from the REST API DB")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = TripBooking.class))),
			@ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = ErrorInfo.class))) })
	@DeleteMapping(value = "/drivers/{driverId}")
	public ResponseEntity<Driver> deleteDriver(@PathVariable("driverId") int driverId) {
		return new ResponseEntity<>(driverSer.deleteDriver(driverId), HttpStatus.OK);
	}

	@Operation(summary = "Fetching Best drivers from the REST API DB")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = TripBooking.class))),
			@ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = ErrorInfo.class))) })
	@GetMapping(value = "/drivers")
	public ResponseEntity<List<Driver>> getBestDriver() {
		List<Driver> bestDriver = driverSer.viewBestDrivers();
		return new ResponseEntity<>(bestDriver, HttpStatus.OK);
	}

	@Operation(summary = "Fetching drivers based on driverId from the REST API DB")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = TripBooking.class))),
			@ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = ErrorInfo.class))) })
	@GetMapping(value = "/drivers/{driverId}")
	public ResponseEntity<Driver> getDriverById(@PathVariable("driverId") int driverId) {
		return new ResponseEntity<>(driverSer.viewDriver(driverId), HttpStatus.OK);
	}
}
