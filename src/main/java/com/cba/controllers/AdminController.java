package com.cba.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cba.entities.Admin;
import com.cba.responses.ErrorInfo;
import com.cba.services.AdminServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired

	AdminServiceImpl i;

	@Operation(summary = "Adding Admin in the Rest API DB")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = Admin.class))),
			@ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = ErrorInfo.class))) })
	@PostMapping(value = "/admin")
	public ResponseEntity<Admin> insertAdmin(@RequestBody Admin a) {
		return new ResponseEntity<>(i.insertAdmin(a), HttpStatus.CREATED);

	}

	@Operation(summary = "Updating Admin in the Rest API DB")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "202", description = "ACCEPTED", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = Admin.class))),
			@ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = ErrorInfo.class))) })
	@PutMapping(value = "/admin/{id}")
	public ResponseEntity<Admin> updateAdmin(@RequestBody Admin a) {
		return new ResponseEntity<>(i.updateAdmin(a), HttpStatus.OK);

	}

	@Operation(summary = "Deleting Admin on the basis of Admin Id from from REST API DB")
	@ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = Admin.class)))
	@ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = ErrorInfo.class)))
	@DeleteMapping("/admin/{id}")
	public ResponseEntity<Admin> deleteAdmin(@PathVariable("id") int adminId) {
		return new ResponseEntity<>(i.deleteAdmin(adminId), HttpStatus.OK);

	}
}
