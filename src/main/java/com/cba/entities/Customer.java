package com.cba.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Customer extends AbstractUser {

//	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
//	private TripBooking tripBooking;

	@OneToMany(mappedBy = "customer")
	@JsonIgnore
	private List<TripBooking> tripBookings;

//	@JsonIgnore
//	public void setCustomerToTripBooking(TripBooking tripBooking) {
//		tripBooking.setCustomer(this);
//	}

}
