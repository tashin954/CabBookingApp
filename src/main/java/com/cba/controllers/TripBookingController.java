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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cba.entities.Driver;
import com.cba.entities.TripBooking;
import com.cba.helper.TakeLocalDate;
import com.cba.helper.TripRequest;
import com.cba.repositories.ITripBookingRepository;
import com.cba.responses.ErrorInfo;
import com.cba.services.TripBookingServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/tripbookings")
public class TripBookingController {
	@Autowired
	private TripBookingServiceImpl tripBookingService;

	@Autowired
	ITripBookingRepository tripBookingRepo;

	@GetMapping("/getAllDrivers")
	public ResponseEntity<List<Driver>> getAllDri() {

		return new ResponseEntity<>(tripBookingRepo.findAllDrivers(), HttpStatus.OK);
	}

	@Operation(summary = "Adding TripBookings in the REST API DB")
	@PostMapping
	public ResponseEntity<String> insertTrip(@Valid @RequestBody TripBooking tripBooking) {

		return new ResponseEntity<>(tripBookingService.insertTripBooking(tripBooking), HttpStatus.OK);
	}

	@Operation(summary = "Updating TripBookings in the REST API DB")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "text/plain", schema = @Schema(oneOf = TripBooking.class))),
			@ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(mediaType = "text/plain", schema = @Schema(oneOf = ErrorInfo.class))) })
	@PutMapping
	public ResponseEntity<String> updateTrip(@Valid @RequestBody TripBooking t) {
		return new ResponseEntity<>(tripBookingService.updateTripBooking(t), HttpStatus.OK);
	}

	@Operation(summary = "Deleting TripBookings on the basis of TripBooking Id from REST API DB")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = TripBooking.class))),
			@ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = ErrorInfo.class))) })
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<TripBooking> deleteTrip(@PathVariable("id") int id) {
		return new ResponseEntity<>(tripBookingService.deleteTripBooking(id), HttpStatus.OK);
	}

	@Operation(summary = "View all TripBookings from the REST API DB")
	@GetMapping(value = "/viewAllTrips")
	public ResponseEntity<List<TripBooking>> viewTrips() {
		return new ResponseEntity<>(tripBookingService.viewAllTrips(), HttpStatus.OK);
	}

	@Operation(summary = "Fetching TripBookings on the basis of Customer Id from the REST API DB")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = TripBooking.class))),
			@ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = ErrorInfo.class))) })
	@GetMapping("/tripsByCustomerId/{customerId}")
	public ResponseEntity<List<TripBooking>> viewAllTripsByCustomerId(@PathVariable("customerId") int customerId) {
		return new ResponseEntity<>(tripBookingService.viewAllTripsCustomer(customerId), HttpStatus.OK);
	}

	@Operation(summary = "Fetching TripBookings on the basis of Date from the REST API DB")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = TripBooking.class))),
			@ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = ErrorInfo.class))) })
	@PostMapping("/getTripsByDate")
	public ResponseEntity<List<TripBooking>> viewAllTripsByDate(@RequestBody TakeLocalDate td) {
		return new ResponseEntity<>(tripBookingService.getTripsByDate(td.getDate()), HttpStatus.OK);
	}

	@Operation(summary = "Fetching TripBookings between two Dates from the REST API DB")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = TripBooking.class))),
			@ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = ErrorInfo.class))) })
	@PostMapping("/betweenDates")
	public ResponseEntity<List<TripBooking>> viewAllTripsBetweenDate(@RequestBody TripRequest tripRequest) {
		return new ResponseEntity<>(
				tripBookingService.getTripsBetweenDate(tripRequest.getFromDate(), tripRequest.getToDate()),
				HttpStatus.OK);
	}

}
