package com.cba.services;

import java.util.List;

import com.cba.entities.Cab;

public interface ICabService {
	public Cab insertCab(Cab cab);

	public Cab updateCab(Cab cab);

	public Cab deleteCab(int cabId);

	public List<Cab> viewCabsOfType(String carType);

	public int countCabsOfType(String carType);

}
