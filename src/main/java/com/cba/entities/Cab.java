package com.cba.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cab {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cabId;

	@NotNull
	@NotBlank
	private String carType;

	@NotNull
	@DecimalMin(value = "20")
	@DecimalMax(value = "100")
	private float perKmRate;

	@OneToOne
	@JsonIgnore
	private Driver driver;
}
