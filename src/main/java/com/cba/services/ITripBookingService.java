package com.cba.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.cba.entities.TripBooking;

public interface ITripBookingService {
	public String insertTripBooking(TripBooking tripBooking);

	public String updateTripBooking(TripBooking tripBooking);

	public TripBooking deleteTripBooking(int tripBookingId);

	public List<TripBooking> viewAllTripsCustomer(int customerId);

	//public String updateTripBookingBillAmount(int tripBookingId);

	public List<TripBooking> getTripsByDate(LocalDate date);
	
	public List<TripBooking> getTripsBetweenDate(LocalDateTime fromDate, LocalDateTime toDate);
}
