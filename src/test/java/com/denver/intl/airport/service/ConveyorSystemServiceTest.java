package com.denver.intl.airport.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.denver.intl.airport.domain.Route;

/**
 * Represent the conveyor system test
 * 
 * @author 	Luis
 * @version 1.0
 */
public class ConveyorSystemServiceTest {

	private ConveyorSystemService conveyorService;
	
	/**
	 * Creating a conveyor system services for the coming tests
	 */
	@Before
	public void setUp() {
		conveyorService = new ConveyorSystemService();
		// Creating a few records of distance between points
		String[] pathRecord1 = {"A5", "A1", "6"};
		String[] pathRecord2 = {"A1", "A2", "1"};
		String[] pathRecord3 = {"A2", "A3", "1"};
		String[] pathRecord4 = {"A3", "A4", "1"};
		String[] pathRecord5 = {"Concourse_A_Ticketing", "A10", "5"};
		
		// Saving the information 
		List<String[]> pathRecords = new ArrayList<>();
		pathRecords.add(pathRecord1);
		pathRecords.add(pathRecord2);
		pathRecords.add(pathRecord3);
		pathRecords.add(pathRecord4);
		pathRecords.add(pathRecord5);
		
		// Saving the point names
		Set<String> pointNames = new HashSet<>();
		pointNames.addAll(Arrays.asList("A1", "A2", "A3", "A4", "A5", "Concourse_A_Ticketing", "A10"));
		
		// Initialize conveyor system
		conveyorService.initializeConveyor(pathRecords, pointNames);
	}

	/**
	 * Testing conveyor system initialization
	 */
	@Test
	public void testConveyorSystemOptimaRoute() {
		// Getting optimal route using the service
		Route optimalRoute = conveyorService.getOptimalRoute("A5", "A3");
		
		// Checking the optimal route total time
		assertTrue(optimalRoute.getTotalTime() == 8);
		
		// Checking route points
		assertTrue(optimalRoute.getPoints().containsAll(Arrays.asList("A5", "A3")));
		
		// Checking point that doesn' belong to the route
		assertFalse(optimalRoute.getPoints().contains("A4"));
		
		// Getting optimal route for no route
		Route optimalRoute2 = conveyorService.getOptimalRoute("A5", "A10");
		
		// Checking the optimal route total time
		assertTrue(optimalRoute2.getTotalTime() == Integer.MAX_VALUE);
	}	

}
