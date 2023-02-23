package com.cba.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cba.entities.Cab;
import com.cba.exceptions.CabIdNotFoundException;
import com.cba.exceptions.CarTypeNotFoundException;
import com.cba.repositories.ICabRepository;

@Service
public class CabServiceImpl implements ICabService {

	@Autowired
	ICabRepository cabRepository;

	@Override
	public Cab insertCab(Cab cab) {
		return cabRepository.save(cab);
	}

	@Override
	public Cab updateCab(Cab cab) {
		Optional<Cab> data = cabRepository.findById(cab.getCabId());
		if (!(data.isPresent()))
			throw new CabIdNotFoundException("No record found for given id ");
		data.get().setCabId(cab.getCabId());
		data.get().setCarType(cab.getCarType());
		data.get().setPerKmRate(cab.getPerKmRate());
		return cabRepository.save(cab);
	}

	@Override
	public Cab deleteCab(int cabId) {
		Cab data = cabRepository.findById(cabId)
				.orElseThrow(() -> new CabIdNotFoundException("Customer not found for ID: " + cabId));
		cabRepository.delete(data);
		return data;
	}

	@Override
	public List<Cab> viewCabsOfType(String carType) {
		List<Cab> cablist = cabRepository.findByCarType(carType);
		if (cablist.isEmpty()) {
			throw new CarTypeNotFoundException("No record found for given cabtype: " + carType);
		}
		return cablist;
	}

	@Override
	public int countCabsOfType(String carType) {
		List<Cab> cablist = cabRepository.findByCarType(carType);
		if (cablist.isEmpty()) {
			throw new CarTypeNotFoundException("No record found for given cabtype: " + carType);
		}
		return cablist.size();
	}

}
