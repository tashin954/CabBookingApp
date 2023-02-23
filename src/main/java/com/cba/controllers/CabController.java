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

import com.cba.entities.Cab;
import com.cba.services.CabServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class CabController {

	@Autowired
	private CabServiceImpl cabService;

	@Operation(summary = "Adding Cab in the REST API DB")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = Cab.class))),
			@ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = Cab.class))) })
	@PostMapping("/cabs")
	public ResponseEntity<Cab> insertCab(@Valid @RequestBody Cab cab) {
		return new ResponseEntity<>(cabService.insertCab(cab), HttpStatus.OK);
	}

	@Operation(summary = "Updating Cab in the REST API DB")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = Cab.class))),
			@ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = Cab.class))) })
	@PutMapping("/cabs")
	public ResponseEntity<Cab> updateCab(@Valid @RequestBody Cab cab) {
		return new ResponseEntity<>(cabService.updateCab(cab), HttpStatus.OK);
	}

	@Operation(summary = "Deleting Cab by Id from the REST API DB")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = Cab.class))),
			@ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = Cab.class))) })
	@DeleteMapping("/cabs/{id}")
	public ResponseEntity<Cab> deleteCabById(@PathVariable("id") int cabId) {
		return new ResponseEntity<>(cabService.deleteCab(cabId), HttpStatus.OK);

	}

	@Operation(summary = "View cabs of type from the REST API DB")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = Cab.class))),
			@ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = Cab.class))) })
	@GetMapping("/cabs/byCabType/{cabType}")
	public ResponseEntity<List<Cab>> viewCabsOfType(@PathVariable("cabType") String cabType) {
		List<Cab> list = cabService.viewCabsOfType(cabType);
		if (list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@Operation(summary = "Count cabs of type present in the REST API DB")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = Cab.class))),
			@ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = Cab.class))) })
	@GetMapping("/cabs/countbycabtype/{cabType}")
	public ResponseEntity<Integer> countCabsOfType(@PathVariable("cabType") String cabType) {
		return new ResponseEntity<>(cabService.countCabsOfType(cabType), HttpStatus.OK);
	}
}
