package com.cba.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cba.entities.Driver;
import com.cba.exceptions.DriverIdNotFoundException;
import com.cba.exceptions.NoBestDriverFoundException;
import com.cba.repositories.IDriverRepository;

@Service
public class DriverServiceImpl implements IDriverService {
	@Autowired
	private IDriverRepository driverRepository;

	@Override
	public Driver insertDriver(Driver driver) {
		driver.setDriverToCab(driver.getCab());
		return driverRepository.save(driver);
	}

	@Override
	public Driver updateDriver(Driver driver) {
		Optional<Driver> data = driverRepository.findById(driver.getUserId());
		if (!(data.isPresent()))
			throw new DriverIdNotFoundException("Driver not found for ID: " + driver.getUserId());
		data.get().setUsername(driver.getUsername());
		data.get().setPassword(driver.getPassword());
		data.get().setAddress(driver.getAddress());
		data.get().setMobileNumber(driver.getMobileNumber());
		data.get().setEmail(driver.getEmail());
		data.get().getCab().setCarType(driver.getCab().getCarType());
		data.get().getCab().setPerKmRate(driver.getCab().getPerKmRate());

		driver.setDriverToCab(driver.getCab());
		return driverRepository.save(data.get());
	}

	@Override
	public Driver deleteDriver(int driverId) {
		Driver data = driverRepository.findById(driverId)
				.orElseThrow(() -> new DriverIdNotFoundException("Driver not found for ID: " + driverId));
		driverRepository.delete(data);
		return data;

	}

	@Override
	public List<Driver> viewBestDrivers() {
		List<Driver> entityList = driverRepository.findAll();
		List<Driver> bestList = new ArrayList<>();
		for (int i = 0; i < entityList.size(); i++) {
			if (entityList.get(i).getRating() >= 4.5) {
				bestList.add(entityList.get(i));
			}
		}
		if (bestList.isEmpty()) {
			throw new NoBestDriverFoundException("No Drivers with rating > 4.5 found");
		}
		return bestList;
	}

	@Override
	public Driver viewDriver(int driverId) {
		Optional<Driver> responseEntity = driverRepository.findById(driverId);
		if (!(responseEntity.isPresent())) {
			throw new DriverIdNotFoundException("Driver not found for ID: " + driverId);
		}
		return responseEntity.get();
	}

}
