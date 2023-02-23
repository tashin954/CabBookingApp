package com.cba.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Driver extends AbstractUser {

	@NotNull
	@NotBlank
	private String licenceNo;

	@DecimalMin(value = "0", inclusive = true)
	@DecimalMax(value = "5", inclusive = true)
	@Schema(description = "The Trip rating from customer", defaultValue = "0")
	private float rating;

	@OneToOne(mappedBy = "driver", cascade = CascadeType.ALL)
	private Cab cab;
//
//	@ManyToOne
//	private TripBooking tripBooking;

	@OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<TripBooking> tripBooking;

	@JsonIgnore
	public void setDriverToCab(Cab cab) {
		cab.setDriver(this);
	}
	
//	@JsonIgnore
//	public void setDriverrToTripBooking(TripBooking tripBooking) {
//		tripBooking.setCustomer(this);
//	}

}
