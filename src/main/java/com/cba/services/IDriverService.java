package com.cba.services;

import java.util.List;

import com.cba.entities.Driver;

public interface IDriverService {
	public Driver insertDriver(Driver driver);

	public Driver updateDriver(Driver driver);

	public Driver deleteDriver(int driverId);

	public List<Driver> viewBestDrivers();

	public Driver viewDriver(int driverId);
}
