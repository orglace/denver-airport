package com.denver.intl.airport.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.denver.intl.airport.domain.FlightInfo;

/**
 * Represent the flight departure information service
 * 
 * @author 	Luis
 * @version 1.0
 */

@Service
public class FlightInfoDashboardService {
	
	private List<FlightInfo> flights;		// The list of departure flights

	/**
	 * The default constructor
	 */
	public FlightInfoDashboardService() {
		this.flights = new ArrayList<>(20);
	}
	
	/**
	 * Add a flight information to the dashboard
	 * 
	 * @param flight A flight departure information
	 */
	public void addFlight(FlightInfo flight) {
		this.flights.add(flight);
	}
	
	/**
	 * Get the flight's departure gate info given a flight id
	 * 
	 * @param flightId A flight id
	 * @return The flight's departure gate
	 */
	public String getGate(String flightId) {
		if(flightId.equals("ARRIVAL")) 
			return "BaggageClaim";
		
		Optional<FlightInfo> flightInfo = this.flights.stream()
				.filter(f -> f.getFlightId().equals(flightId))
				.findFirst();
		return flightInfo.isPresent()? flightInfo.get().getGate(): null;
	}
}
