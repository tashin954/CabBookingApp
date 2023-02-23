package com.cba.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cba.entities.Customer;
import com.cba.entities.Driver;
import com.cba.entities.TripBooking;

@Repository
public interface ITripBookingRepository extends JpaRepository<TripBooking, Integer> {
	public Optional<TripBooking> findByTripBookingId(int id);

	public List<TripBooking> findByCustomer(Customer customer);

	@Query("select obj.customer from TripBooking obj where obj.customer.userId=?1")
	public Customer returnCustomer(int id);

	@Query("SELECT DISTINCT tb.driver FROM TripBooking tb")
	List<Driver> findAllDrivers();

	public List<TripBooking> findAllByFromDateTimeBetween(LocalDateTime fromDate, LocalDateTime toDate);

}
