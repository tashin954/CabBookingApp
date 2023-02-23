package com.cba.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cba.entities.Cab;
import com.cba.entities.Customer;
import com.cba.entities.Driver;
import com.cba.entities.TripBooking;
import com.cba.exceptions.DriverIdNotFoundException;
import com.cba.exceptions.NoBestDriverFoundException;
import com.cba.repositories.IDriverRepository;
import com.cba.repositories.ITripBookingRepository;
import com.cba.services.DriverServiceImpl;
import com.cba.services.TripBookingServiceImpl;

@SpringBootTest
class CabBookingApplicationTests {

	@Test
	void contextLoads() {
	}

	@Mock
	private ITripBookingRepository tripBookingRepository;

	@InjectMocks
	private TripBookingServiceImpl tripBookingService;

	@InjectMocks
	private DriverServiceImpl driverServiceImpl;

	@Mock
	private IDriverRepository driverRepository;

	private List<TripBooking> trips;

	private TripBooking trip;

	private Customer cus1;
	private Cab c1;
	private Driver d1;
	private LocalDateTime dateTime;
	private List<Driver> driverList;
	private LocalDate date;

	@BeforeEach
	public void setUp() {

		dateTime = LocalDateTime.of(2023, 2, 11, 12, 30, 0);
		date = LocalDate.of(2022, 2, 14);
		trips = new ArrayList<TripBooking>();
		driverList = new ArrayList<>();

		cus1 = new Customer();
		cus1.setUserId(1);
		cus1.setUsername("Customer1");
		cus1.setPassword("ghij123");
		cus1.setAddress("pune");
		cus1.setMobileNumber("9971234557");
		cus1.setEmail("cus1@gmail.com");

		c1 = new Cab();
		c1.setCabId(1);
		c1.setCarType("Tata Nano");
		c1.setPerKmRate(50.0f);

		d1 = new Driver();
		d1.setUserId(2);
		d1.setUsername("Driver1");
		d1.setPassword("abcdef123");
		d1.setAddress("pune");
		d1.setMobileNumber("9871234567");
		d1.setEmail("driver1@gmail.com");
		d1.setLicenceNo("LDRT56");
		d1.setCab(c1);
		d1.setRating(4);

		c1.setDriver(d1);
		driverList.add(d1);

		trip = new TripBooking();
		trip.setTripBookingId(2);
		trip.setFromLocation("pune");
		trip.setToLocation("mumbai");
		trip.setFromDateTime(dateTime);
		trip.setFromDateTime(dateTime);
		trip.setDistanceInKm(300);
		//trip.setDriverList(driverList);
		trip.setCustomer(cus1);
		trip.setBill(2000);
		trip.setBookingStatus(false);

		trips.add(trip);
	}

	@Test
	   void testViewAllTrips() {
	   
	    when(tripBookingRepository.findAll()).thenReturn(trips);

	    List<TripBooking> result = tripBookingService.viewAllTrips();

	    assertEquals(trips, result);
	  }

//	@Test
//	   void testInsertTripBooking() {
//	    when(tripBookingRepository.save(trip)).thenReturn(trip);
//
//	    TripBooking result = tripBookingService.insertTripBooking(trip);
//
//	    assertEquals(trip, result);
//	  }
//
//	@Test
//	void testUpdateTripBooking() {
//
//		TripBooking tripBooking = new TripBooking();
//		tripBooking.setTripBookingId(10);
//		tripBooking.setBill(4000);
//
//		when(tripBookingRepository.findByTripBookingId(10)).thenReturn(Optional.of(tripBooking));
//		when(tripBookingRepository.save(tripBooking)).thenReturn("Details updated successfully");
//
//		String updatedTripBooking = tripBookingService.updateTripBooking(tripBooking);
//
//		assertEquals(updatedTripBooking, "Details updated successfully");
//	}

	@Test
	 void testDeleteTripBooking() {

		when(tripBookingRepository.findById(2)).thenReturn(Optional.of(trip));
		doNothing().when(tripBookingRepository).delete(trip);

		TripBooking deletedTripBooking = tripBookingService.deleteTripBooking(2);

		assertEquals(trip, deletedTripBooking);

		verify(tripBookingRepository).delete(trip);
	}

	@Test
	     void testGetTripsBetweenDate() {
	        when(tripBookingRepository.findAllByFromDateTimeBetween(dateTime, dateTime)).thenReturn(trips);
	        
	        List<TripBooking> result = tripBookingService.getTripsBetweenDate(dateTime, dateTime);
	        
	        assertEquals(1, result.size());
	        assertEquals(trip, result.get(0));
	 }

	@Test
	void testInsertDriver() {
		Driver driver = new Driver();
		driver.setCab(new Cab());
		driver.setDriverToCab(driver.getCab());

		when(driverRepository.save(driver)).thenReturn(driver);

		Driver insertedDriver = driverServiceImpl.insertDriver(driver);

		assertEquals(driver, insertedDriver);
	}

	@Test
	void testUpdateDriver_Success() {
		Driver driver = new Driver();
		driver.setUserId(1);
		driver.setUsername("test_user");
		driver.setPassword("test_password");
		driver.setAddress("test_address");
		driver.setMobileNumber("1234567890");
		driver.setEmail("test@email.com");

		Cab cab = new Cab();
		cab.setCarType("test_car_type");
		cab.setPerKmRate(100.0f);
		driver.setCab(cab);

		Optional<Driver> driverOptional = Optional.of(driver);
		when(driverRepository.findById(1)).thenReturn(driverOptional);
		when(driverRepository.save(driver)).thenReturn(driver);

		Driver updatedDriver = driverServiceImpl.updateDriver(driver);

		assertEquals(driver.getUserId(), updatedDriver.getUserId());
		assertEquals(driver.getUsername(), updatedDriver.getUsername());
		assertEquals(driver.getPassword(), updatedDriver.getPassword());
		assertEquals(driver.getAddress(), updatedDriver.getAddress());
		assertEquals(driver.getMobileNumber(), updatedDriver.getMobileNumber());
		assertEquals(driver.getEmail(), updatedDriver.getEmail());
		assertEquals(driver.getCab().getCarType(), updatedDriver.getCab().getCarType());
		assertEquals(driver.getCab().getPerKmRate(), updatedDriver.getCab().getPerKmRate(), 0.0);
	}

	public void testUpdateDriver_DriverIdNotFoundException() {
		Driver driver = new Driver();
		driver.setUserId(2);

		DriverIdNotFoundException exception = assertThrows(DriverIdNotFoundException.class, () -> {
			driverServiceImpl.updateDriver(driver);
		});
		assertEquals("Driver not found for ID: " + driver.getUserId(), exception.getMessage());

	}

//	@BeforeEach
//	void setUp() {
//		MockitoAnnotations.initMocks(this);
//	}

	@Test
	void testDeleteDriverSuccess() {
		int driverId = 1;
		Driver expectedDriver = new Driver();
		expectedDriver.setUserId(driverId);
		when(driverRepository.findById(driverId)).thenReturn(Optional.of(expectedDriver));
		Driver actualDriver = driverServiceImpl.deleteDriver(driverId);
		assertEquals(expectedDriver, actualDriver);
	}

	@Test
	void testDeleteDriverFailure() {
		int driverId = 2;
		when(driverRepository.findById(driverId)).thenReturn(Optional.empty());
		DriverIdNotFoundException exception = assertThrows(DriverIdNotFoundException.class, () -> {
			driverServiceImpl.deleteDriver(driverId);
		});
		assertEquals("Driver not found for ID: " + driverId, exception.getMessage());
	}

	@Test
	void testViewBestDrivers() {

		Cab cab = new Cab();
		cab.setCarType("test_car_type");
		cab.setPerKmRate(100.0f);

		Driver driver1 = new Driver();
		driver1.setUserId(100);
		driver1.setUsername("John");
		driver1.setRating(4.6f);
		driver1.setCab(cab);
		Driver driver2 = new Driver();
		driver2.setUserId(101);
		driver2.setUsername("Jane");
		driver2.setRating(4.4f);
		driver2.setCab(cab);
		Driver driver3 = new Driver();
		driver3.setUserId(102);
		driver3.setUsername("Jim");
		driver3.setRating(4.8f);
		driver3.setCab(cab);

		List<Driver> bestDriversList = new ArrayList<>();
		bestDriversList.add(driver1);
		bestDriversList.add(driver2);
		bestDriversList.add(driver3);

		when(driverRepository.findAll()).thenReturn(bestDriversList);
		List<Driver> bestDrivers = driverServiceImpl.viewBestDrivers();
		assertEquals(2, bestDrivers.size());
	}

	public void testViewBestDriversNoDriversFound() {
		driverRepository.deleteAll();
		driverServiceImpl.viewBestDrivers();
		when(driverRepository.findAll()).thenReturn(new ArrayList<>());
		NoBestDriverFoundException exception = assertThrows(NoBestDriverFoundException.class, () -> {
			driverServiceImpl.viewBestDrivers();
		});

		assertEquals("No Drivers with rating > 4.5 found", exception.getMessage());
	}

	@Test
	void testViewDriver_validId_returnsDriver() {
		Driver expectedDriver = new Driver();
		expectedDriver.setUserId(1);
		expectedDriver.setUsername("John");
		expectedDriver.setPassword("password");
		expectedDriver.setAddress("1 Main St");
		expectedDriver.setMobileNumber("1234567890");
		expectedDriver.setEmail("john@email.com");

		Cab cab = new Cab();
		cab.setCarType("sedan");
		cab.setPerKmRate(10.0f);
		expectedDriver.setDriverToCab(cab);

		when(driverRepository.findById(1)).thenReturn(Optional.of(expectedDriver));

		Driver actualDriver = driverServiceImpl.viewDriver(1);

		assertEquals(expectedDriver, actualDriver);
	}

	@Test
	     void testViewDriver_invalidId_throwsException() {
	        when(driverRepository.findById(2)).thenReturn(Optional.empty());

	        try {
	        	driverServiceImpl.viewDriver(2);
	            fail("Expected an DriverIdNotFoundException to be thrown");
	        } catch (DriverIdNotFoundException e) {
	            assertEquals("Driver not found for ID: 2", e.getMessage());
	        }
	    }

//	@Test
//	public void testViewAllTripsCustomer() {
//		when(tripBookingRepository.findByCustomer(cus1)).thenReturn(trips);
//        
//        List<TripBooking> result = tripBookingService.viewAllTripsCustomer(1);
//        
//        assertEquals(1, result.size());
//        assertEquals(trip, result.get(0));
//	}
//
//	@Test
//    public void testGetTripsByDate() {
//        when(tripBookingRepository.findAll()).thenReturn(trips);
//        
//        List<TripBooking> result = tripBookingService.getTripsByDate(date);
//        
//        assertEquals(1, result.size());
//       // assertEquals(trip.get, result.get(0));
//	}

}
