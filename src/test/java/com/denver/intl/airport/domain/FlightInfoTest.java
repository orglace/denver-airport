package com.denver.intl.airport.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import com.denver.intl.airport.domain.FlightInfo;

/**
 * @author 	Luis
 * @version 1.0
 */
public class FlightInfoTest {

	private FlightInfo flightinfo;
	
	/**
	 * Create a FlightInfo for the coming tests
	 */
	@Before
	public void init() {
		flightinfo = new FlightInfo("UA10", "A1", "MIA", LocalTime.parse("08:00"));
	}
	
	/**
	 * Test the FlightInfo attributes getters
	 */
	@Test
	public void testFlightInfoGetters() {
		// Checking flight's id have been init
		assertSame(flightinfo.getFlightId(), "UA10");
		
		// Checking flight's gate have been init
		assertSame(flightinfo.getGate(), "A1");
		
		// Checking flight's destination have been init
		assertSame(flightinfo.getDestiantion(), "MIA");
		
		// Checking flight's time have been init
		assertTrue(flightinfo.getTime().getHour() == 8);
	}
	
	/**
	 * Test the FlightInfo attributes setters
	 */
	@Test
	public void testFlightInfoSetters() {
		// Change and check the flight's id
		flightinfo.setFlightId("UA8");
		assertNotSame(flightinfo.getFlightId(), "UA10");
		
		// Change and check the flight's gate
		flightinfo.setGate("A7");
		assertNotSame(flightinfo.getGate(), "A1");
		
		// Change and check the flight's destination
		flightinfo.setDestiantion("LAX");
		assertNotSame(flightinfo.getDestiantion(), "MIA");
		
		// Change and check the flight's time
		flightinfo.setTime(LocalTime.of(5, 45));
		assertFalse(flightinfo.getTime().getMinute() == 20);
	}
}
