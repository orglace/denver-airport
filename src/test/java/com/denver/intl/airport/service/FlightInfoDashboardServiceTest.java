package com.denver.intl.airport.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import com.denver.intl.airport.domain.FlightInfo;

/**
 * Represent the flight info service tests
 * 
 * @author 	Luis
 * @version 1.0
 */
public class FlightInfoDashboardServiceTest {

	private FlightInfoDashboardService flightInfoService;
	
	/**
	 * Creating a flight info services for the coming tests
	 */
	@Before
	public void setUp() {
		flightInfoService = new FlightInfoDashboardService();
	}

	/**
	 * Testing adding new flights info to through the service
	 */
	@Test
	public void testAddingFlightInfo() {
		// Creating multiples flight departures info records
		FlightInfo flightinfo1 = new FlightInfo("UA10", "A1", "MIA", LocalTime.parse("08:00"));
		FlightInfo flightinfo2 = new FlightInfo("UA11", "A7", "LAX", LocalTime.parse("12:00"));
		
		// Updating the dashboard with the new information
		flightInfoService.addFlight(flightinfo1);
		flightInfoService.addFlight(flightinfo2);
		
		// Checking the flight information using the service
		assertNotNull(flightInfoService.getGate("UA10"));
	}
	
	/**
	 * Testing getting the flight departure gate
	 */
	@Test
	public void testGetFlightDepartureGate() {
		// Creating a flight departures info record
		FlightInfo flightinfo3 = new FlightInfo("UA13", "A2", "JFK", LocalTime.parse("08:30"));
		
		// Updating the dashboard with the new information
		flightInfoService.addFlight(flightinfo3);
		
		// Checking the flight gate for a wrong flight id
		assertNull(flightInfoService.getGate("WRONGID"));
		
		// Checking the flight gate for arrival flight
		assertSame(flightInfoService.getGate("ARRIVAL"), "BaggageClaim");
	}

}
