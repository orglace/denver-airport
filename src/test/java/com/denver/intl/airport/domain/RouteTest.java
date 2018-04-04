package com.denver.intl.airport.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Before;
import org.junit.Test;

import com.denver.intl.airport.domain.Route;

/**
 * @author Luis
 *
 */
public class RouteTest {
	
	private Route initialRoute;
	
	/**
	 * Create a route to reuse in the coming tests
	 */
	@Before
	public void init() {

		// Creating initial to use in all the test
		AtomicInteger time = new AtomicInteger(1);
		initialRoute = new Route();
		Arrays.asList("A1", "A2", "A3")
			.forEach(pointName -> {
				initialRoute.addPoint(pointName);
				initialRoute.incrementTotalTime(time.getAndIncrement());
			});
	}
	
	/**
	 * Testing set the route total time
	 */
	@Test
	public void testSetTotalTime() {
		initialRoute.setTotalTime(Integer.MAX_VALUE);
		assertFalse(initialRoute.getTotalTime() == 6);
	}
	
	/**
	 * Testing the route default constructor
	 */
	@Test
	public void testCreateInstanceWithDefaultContructor() {
		Route route = new Route();
		
		// Checking default total time
		assertTrue(route.getTotalTime() == 0);
		// Checking default route points
		assertTrue(route.getPoints().isEmpty());
	}
	
	/**
	 * Testing the route constructor based in a parent route
	 */
	@Test
	public void testCreatingRouteWithParentRoute() {
		Route route = new Route(initialRoute);
		
		// Checking total time getting from a parent route
		assertTrue(route.getTotalTime() == 6);
		// Checking number of point getting from a parent route
		assertTrue(route.getPoints().size() == 3);
	}
	
	/**
	 * Testing adding a new point to the route
	 */
	@Test
	public void testCheckAddingNewPointsRoute() {
		Route route = new Route(initialRoute);
		// Adding new points and time to the route
		route.addPoint("A4");
		route.incrementTotalTime(1);
		
		route.addPoint("A5");
		route.incrementTotalTime(1);
		
		route.addPoint("A6");
		route.incrementTotalTime(2);
		
		// Checking total time after new points added
		assertTrue(route.getTotalTime() == 10);
		// Checking number of point after new ones added
		assertTrue(route.getPoints().size() == 6);
	}
	
	/**
	 * Testing if there is an existing point inside the route
	 */
	@Test
	public void testCheckExitingPointInRoute() {
		Route route = new Route(initialRoute);
		// Check if a point already exist in the route
		if(!route.isVisited("A4"))
			route.addPoint("A4");
		
		// Checking number of point affter adding one more
		assertTrue(route.getPoints().size() == 4);
	}
}
