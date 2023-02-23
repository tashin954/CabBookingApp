package com.cba.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cba.entities.Customer;
import com.cba.entities.Driver;
import com.cba.entities.TripBooking;
import com.cba.exceptions.CustomerIdNotFoundException;
import com.cba.exceptions.NoTripsOnThisDateException;
import com.cba.exceptions.TripNotFoundException;
import com.cba.repositories.IDriverRepository;
import com.cba.repositories.ITripBookingRepository;

@Service
public class TripBookingServiceImpl implements ITripBookingService {
	@Autowired
	private ITripBookingRepository tripBookingRepository;

	@Autowired
	IDriverRepository driverRepo;

	public List<TripBooking> viewAllTrips() {
		return tripBookingRepository.findAll();
	}

	@Override
	public String insertTripBooking(TripBooking tripBooking) {
		float bill = 0.0f;
		float minValue = Float.MAX_VALUE;

		HashMap<Driver, Float> map = new HashMap<>();
		List<Driver> driverList = driverRepo.findAll();
		if (!(driverList.isEmpty())) {
			for (Driver d : driverList) {
				map.put(d, d.getCab().getPerKmRate());
			}
			for (Entry<Driver, Float> entry : map.entrySet()) {
				float value = entry.getValue();
				if (value < minValue) {
					minValue = value;
					tripBooking.setDriver(entry.getKey());
				}
			}
			bill = minValue * tripBooking.getDistanceInKm();

			tripBooking.setBill(bill);
			tripBooking.setBookingStatus(true);
			tripBookingRepository.save(tripBooking);

			return "{\n Customer name: "
					+ ((tripBookingRepository.returnCustomer(tripBooking.getCustomer().getUserId())).getUsername())
					+ "\n Trip Booking Date: " + tripBooking.getFromDateTime() + "\n Booking status: true"
					+ "\n Bill Amount: " + tripBooking.getBill() + "\n Driver Id: "
					+ tripBooking.getDriver().getUserId() + "\n}";

		} else {
			tripBooking.setBill(0);
			tripBooking.setBookingStatus(false);
			tripBookingRepository.save(tripBooking);
			return "{\n Customer name: " + tripBooking.getCustomer().getUsername() + "\n Trip Booking Date: "
					+ tripBooking.getFromDateTime() + "\n Booking status: false" + "\n Bill Amount: "
					+ tripBooking.getBill() + "\n Driver Id: Searching" + "\n}";
		}
	}

	@Override
	public String updateTripBooking(TripBooking tripBooking) {
		Optional<TripBooking> data = tripBookingRepository.findByTripBookingId(tripBooking.getTripBookingId());
		if (!(data.isPresent()))
			throw new TripNotFoundException("Trip not found for ID: " + tripBooking.getTripBookingId());
		data.get().setFromLocation(tripBooking.getFromLocation());
		data.get().setToLocation(tripBooking.getToLocation());
		data.get().setFromDateTime(tripBooking.getFromDateTime());
		data.get().setToDateTime(tripBooking.getToDateTime());
		data.get().setDistanceInKm(tripBooking.getDistanceInKm());

		insertTripBooking(data.get());

		// return tripBookingRepository.save(data.get());
		return "Details updated successfully";
	}

	@Override
	public TripBooking deleteTripBooking(int tripBookingId) {

		TripBooking data = tripBookingRepository.findById(tripBookingId)
				.orElseThrow(() -> new TripNotFoundException("Trip not found for ID: " + tripBookingId));
		tripBookingRepository.delete(data);
		return data;

	}

	@Override
	public List<TripBooking> viewAllTripsCustomer(int customerId) {
		Customer customer = new Customer();
		customer.setUserId(customerId);
		if (tripBookingRepository.findByCustomer(customer).isEmpty()) {
			throw new CustomerIdNotFoundException("No Customer found with Id: " + customerId);
		} else {
			return tripBookingRepository.findByCustomer(customer);
		}
	}

	public List<TripBooking> getTripsByDate(LocalDate date) {
		List<TripBooking> tripList = tripBookingRepository.findAll().stream()
				.filter(trip -> trip.getFromDateTime().toLocalDate().equals(date)
						|| trip.getToDateTime().toLocalDate().equals(date))
				.collect(Collectors.toList());
		if (tripList.isEmpty()) {
			throw new NoTripsOnThisDateException("No TripBooking found on this data: " + date);
		}
		return tripList;
	}

	public List<TripBooking> getTripsBetweenDate(LocalDateTime fromDate, LocalDateTime toDate) {
		List<TripBooking> tripList = tripBookingRepository.findAllByFromDateTimeBetween(fromDate, toDate);
		if (tripList.isEmpty()) {
			throw new NoTripsOnThisDateException("No TripBooking found between this data: " + fromDate + " " + toDate);
		}
		return tripList;
	}

}
