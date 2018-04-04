package com.denver.intl.airport.domain;

import java.time.LocalTime;

/**
 * Represent a flight information inside the Departures dashboard
 * 
 * @author 	Luis
 * @version 1.0
 */
public class FlightInfo {
	
	private String flightId;		// The flight's id
	private String gate;			// The flight's departure gate
	private String destiantion;		// The flight's destination
	private LocalTime time;			// The flight's departure time

	/**
	 * Create a flight information with the given information
	 * 
	 * @param flightId The flight's id
	 * @param gate The flight's gate
	 * @param destiantion The flight's destination
	 * @param time The flight's departure time
	 */
	public FlightInfo(String flightId, String gate, String destiantion, LocalTime time) {
		this.flightId = flightId;
		this.gate = gate;
		this.destiantion = destiantion;
		this.time = time;
	}

	/**
	 * Get the flight's id information
	 * 
	 * @return The flight id
	 */
	public String getFlightId() {
		return flightId;
	}

	/**
	 * Set the flight's id
	 * 
	 * @param flightId The flight id
	 */
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	/**
	 * Get the flight's departure gate
	 * 
	 * @return The flight's gate
	 */
	public String getGate() {
		return gate;
	}

	/**
	 * Set the flight's departure gate 
	 * 
	 * @param gate The flight's departure gate
	 */
	public void setGate(String gate) {
		this.gate = gate;
	}
	
	/**
	 * Get the flight's destination
	 * 
	 * @return The flight's destination
	 */
	public String getDestiantion() {
		return destiantion;
	}

	/**
	 * Set the flight's destination
	 * 
	 * @param destiantion The flight's destination
	 */
	public void setDestiantion(String destiantion) {
		this.destiantion = destiantion;
	}

	/**
	 * Get the flight's departure time
	 * 
	 * @return The flight's time
	 */
	public LocalTime getTime() {
		return time;
	}

	/**
	 * Set The flight's departure time
	 * 
	 * @param time The flight's departure time
	 */
	public void setTime(LocalTime time) {
		this.time = time;
	}
}
