package com.cba.helper;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TripRequest {
	private LocalDateTime fromDate;
	private LocalDateTime toDate;
}
