package com.cba.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cba.entities.Admin;

@Repository("ar")
public interface IAdminRepository extends JpaRepository<Admin, Integer> {

}