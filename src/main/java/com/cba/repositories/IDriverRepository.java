package com.cba.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cba.entities.Driver;

@Repository
public interface IDriverRepository extends JpaRepository<Driver, Integer> {
	
}
