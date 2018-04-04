package com.denver.intl.airport.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.denver.intl.airport.domain.Route;

/**
 * Represent Conveyor System service
 * 
 * @author 	Luis
 * @version 1.0
 */
@Service
public class ConveyorSystemService {
	private final int INFINITY;					// Max distance between two conveyor's points
	private int nPoints;						// Amount of points inside conveyor system
	private List<String> conveyorPoints;		// List of points inside of conveyor system 
	private int[][] adjMatrix;					// Distance information between points (adjacency matrix)
	
	/**
	 * The default constructor for the service
	 */
	public ConveyorSystemService() {
		INFINITY = Integer.MAX_VALUE;
		this.nPoints = 10;
		this.conveyorPoints = new ArrayList<>(nPoints);
		this.adjMatrix = new int[nPoints][nPoints];
	} 
	
	/**
	 * Initialize the conveyor system using the distances between the points
	 * and update inside the adjacency matrix
	 * 
	 * @param pathDistances The distance between the different points inside the system
	 * @param pointNames The list of point names
	 */
	public void initializeConveyor(List<String[]> pathDistances, Set<String> pointNames) {
		this.nPoints = pointNames.size();		// Updating the amount of points getting from the input
		this.conveyorPoints = new ArrayList<>(pointNames);
		this.adjMatrix = new int[nPoints][nPoints];
		
		// Initializing the adjacency matrix 
		for (int r = 0; r < nPoints; r++) {
			for (int c = 0; c < nPoints; c++) {
				this.adjMatrix[r][c] = INFINITY;
			}
		}
		
		// Updating the distance in the adjacency matrix
		pathDistances.forEach(pathRecord -> {
			int r = getPointIndexByName(pathRecord[0]);
			int c = getPointIndexByName(pathRecord[1]);
			int distance = Integer.parseInt(pathRecord[2]);
			this.adjMatrix[r][c] = distance;
			this.adjMatrix[c][r] = distance;
		});
	}
	
	/**
	 * Get the index of the point in the list given a point's name
	 * 
	 * @param pointName The point name
	 * @return the point's index in the list of points
	 */
	private int getPointIndexByName(String pointName) {
		return conveyorPoints.indexOf(pointName);
	}
	
	/**
	 * Get the name of the point in the list given a point's index
	 * 
	 * @param pointIndex The point's index in the list of points
	 * @return The name of the point in the given index
	 */
	private String getPointNameByIndex(int pointIndex) {
		return conveyorPoints.get(pointIndex);
	}
	
	/**
	 * Get the optimal route between two points given the names
	 * 
	 * @param startPointName The initial point name in the route
	 * @param endPointName The final point name in the route
	 * @return the optimal route between two points names
	 */
	public Route getOptimalRoute(String startPointName, String endPointName) {
		int startPointIndex = getPointIndexByName(startPointName);	// Getting the start point index in the route
		int endPointIndex = getPointIndexByName(endPointName);		// Getting the end point index in the route
		
		Route startRoute = new Route();
		startRoute.addPoint(startPointName);
		
		return getOptimalRoute(startPointIndex, endPointIndex, startRoute);
	}

	/**
	 * Get the optimal route between two points given the indexes
	 * 
	 * @param currentPointIndex The initial point index in the route
	 * @param endPointIndex The final point index in the route
	 * @param parentRoute The parent route for a sub route 
	 * @return the optimal route between two point indexes 
	 */
	private Route getOptimalRoute(int currentPointIndex, int endPointIndex, Route parentRoute) {
		
		int currentToEndPointTime = adjMatrix[currentPointIndex][endPointIndex];
		
		Route currentToEndPointRoute = new Route(parentRoute);
		currentToEndPointRoute.addPoint(getPointNameByIndex(endPointIndex));
		if (currentToEndPointTime == INFINITY) {
			currentToEndPointRoute.setTotalTime(INFINITY);
		} else {
			currentToEndPointRoute.incrementTotalTime(currentToEndPointTime);
		}
		
		for (int c = 0; c < nPoints; c++) {
			
			if(c != endPointIndex && !parentRoute.isVisited(getPointNameByIndex(c)) && 
					adjMatrix[currentPointIndex][c] != INFINITY) {
				
				Route newParentRoute = new Route(parentRoute);
				newParentRoute.addPoint(getPointNameByIndex(c));
				newParentRoute.incrementTotalTime(adjMatrix[currentPointIndex][c]);
				
				Route tempRoute = getOptimalRoute(c, endPointIndex, newParentRoute);
				currentToEndPointRoute = (tempRoute.getTotalTime() < currentToEndPointRoute.getTotalTime()? tempRoute: currentToEndPointRoute);
			}
		}
		
		return currentToEndPointRoute;
	}
}
