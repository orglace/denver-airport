package com.denver.intl.airport.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Represent a route inside the Conveyor System
 * 
 * @author 	Luis
 * @version 1.0
 */
public class Route {
	
	private int totalTime;			// Total time to traverse the route
	private List<String> points;	// Points inside the route
	
	/**
	 * Create an empty route
	 */
	public Route() {
		this.totalTime = 0;
		this.points = new ArrayList<>();
	}
	
	/**
	 * Create a route from a parent route
	 * 
	 * @param parentRoute This is the parent route to initialize the new route
	 */
	public Route(Route parentRoute) {
		this.totalTime = parentRoute.getTotalTime();
		this.points = new ArrayList<>(parentRoute.getPoints());
	}

	/**
	 * Get the total time to traverse the route
	 * 
	 * @return the total time
	 */
	public int getTotalTime() {
		return totalTime;
	}

	/**
	 * Change the total time to traverse the route
	 * 
	 * @param totalTime This is the new total time to traverse the route
	 */
	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}

	/**
	 * Increment the total time of the route
	 * 
	 * @param time A portion of time between to point
	 */
	public void incrementTotalTime(int time) {
		this.totalTime += time;
	}

	/**
	 * Get all the point inside the complete route
	 * 
	 * @return The point of the route
	 */
	public List<String> getPoints() {
		return points;
	}
	
	/**
	 * Add a new point to the end of the route
	 * 
	 * @param pointName The new point name in the route
	 */
	public void addPoint(String pointName) {
		this.points.add(pointName);
	}
	
	/**
	 * Check if a given point name is already inside the route
	 * 
	 * @param pointName The point name to check
	 * @return If a given point is in the route
	 */
	public boolean isVisited(String pointName) {
		return this.points.contains(pointName);
	}
}
