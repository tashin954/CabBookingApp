package com.cba.entities;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Entity
@Data
public class TripBooking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int tripBookingId;

	@NotNull
	@NotBlank
	@Size(min = 3, max = 50)
	@Schema(description = "The Boarding location", defaultValue = "Boarding location")
	private String fromLocation;

	@NotNull
	@NotBlank
	@Size(min = 3, max = 50)
	@Schema(description = "The Destination location", defaultValue = "Destination location")
	private String toLocation;

	private LocalDateTime fromDateTime;

	private LocalDateTime toDateTime;

	@NotNull
	@Schema(description = "The Trip distance", defaultValue = "100")
	private float distanceInKm;

	@NotNull
	@Schema(description = "The booking status of Trip", defaultValue = "false")
	private boolean bookingStatus;

	@NotNull
	@Min(value = 0)
	private float bill;

	@ManyToOne
	private Customer customer;

	@ManyToOne
	@JsonIgnore
	private Driver driver;

}
