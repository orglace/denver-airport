package com.denver.intl.airport;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.denver.intl.airport.domain.FlightInfo;
import com.denver.intl.airport.domain.Route;
import com.denver.intl.airport.service.ConveyorSystemService;
import com.denver.intl.airport.service.FlightInfoDashboardService;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@Profile("!test")
public class DenverAirportApplication implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(DenverAirportApplication.class);

	@Autowired
	private ConveyorSystemService conveyorSystemService;
	
	@Autowired
	private FlightInfoDashboardService flightInfoDashboardService;

	@Override
	public void run(String... args) {
		
		List<String[]> pathDistances = new ArrayList<>();
		Set<String> pointNames = new HashSet<>();
		final String sectionHeader = "# Section:"; //Section header prefix
		
		//Creating scanner for reading the application input 
		try (Scanner in = new Scanner(System.in)) { 
			
			//Reading first line and validating the Section Header Format
			String input = in.nextLine();
			if(!input.startsWith(sectionHeader)) {
				logger.error("Input format error [{}]", input);
				return;
			}
			
			//Reading Section that describe the conveyor system information
			while (!(input = in.nextLine()).startsWith(sectionHeader)) {
				if(!input.matches("\\w+\\s\\w+\\s\\d+")) {
					logger.error("Conveyor System information input format error [{}]", input);
					return;
				}
				
				String[] pathRecord = input.split("\\s+");
				pathDistances.add(pathRecord);
				pointNames.addAll(Arrays.asList(pathRecord[0], pathRecord[1]));
			}
			
			conveyorSystemService.initializeConveyor(pathDistances, pointNames);
			
			//Reading Section for Departure information
			while (!(input = in.nextLine()).startsWith(sectionHeader)) {
				if(!input.matches("\\w+\\s\\w+\\s\\w+\\s([01][0-9]|2[0-3]):[0-5][0-9]")) {
					logger.error("Departure information input format error [{}]", input);
					return;
				}
				
				String[] departureRecord = input.split("\\s+");
				String flightId = departureRecord[0];
				String flightGate = departureRecord[1];
				String flightDestiantion = departureRecord[2];
				LocalTime flightTime = LocalTime.parse(departureRecord[3]);
				
				FlightInfo flightInfo = new FlightInfo(flightId, flightGate, flightDestiantion, flightTime);
				flightInfoDashboardService.addFlight(flightInfo);
			}
			
			List<String[]> bagRecords = new ArrayList<>();
			//Reading Section for bag list
			while (!(input = in.nextLine()).isEmpty()) {
				if(!input.matches("\\w+\\s\\w+\\s\\w+")) {
					logger.error("Bags list information input format error [{}]", input);
					return;
				}
				String[] bagRecord = input.split("\\s+");
				bagRecords.add(bagRecord);
			}
			
			displayOutput(bagRecords);
		} 
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(DenverAirportApplication.class, args);
	}
	
	private void displayOutput(List<String[]> bagRecords) {
		
		System.out.println("Example Output:");
		
		bagRecords.forEach(bagRecordInf -> {
			String bagId = bagRecordInf[0];
			String startPointName = bagRecordInf[1];
			String flightId = bagRecordInf[2];
			String endPointName = flightInfoDashboardService.getGate(flightId);
			
			Route optimalRoute = conveyorSystemService.getOptimalRoute(startPointName, endPointName);
			displayRoute(bagId, optimalRoute);
		});
	}
	
	private void displayRoute(String bagId, Route route) {
		System.out.printf("%s ", bagId);
		route.getPoints().forEach(pointName -> System.out.printf("%s ", pointName));
		System.out.println(": " + route.getTotalTime());
	}
}
