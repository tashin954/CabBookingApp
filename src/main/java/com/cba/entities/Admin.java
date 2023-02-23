package com.cba.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Admin")
@NoArgsConstructor

public class Admin extends AbstractUser {
	public Admin(int i, String string, String string2, String string3, String string4, String string5) {
	
	}
}
