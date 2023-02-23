package com.cba.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cba.entities.Cab;

@Repository
public interface ICabRepository extends JpaRepository<Cab, Integer> {
	public List<Cab> findByCarType(String carType);
}
