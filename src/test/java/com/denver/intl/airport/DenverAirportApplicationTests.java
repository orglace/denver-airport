package com.denver.intl.airport;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.springframework.boot.test.rule.OutputCapture;

public class DenverAirportApplicationTests {

	@Rule
	public OutputCapture outputCapture = new OutputCapture();

	private String profiles;

	@Before
	public void init() {
		this.profiles = System.getProperty("spring.profiles.active");
	}

	@After
	public void after() {
		if (this.profiles != null) {
			System.setProperty("spring.profiles.active", this.profiles);
		}
		else {
			System.clearProperty("spring.profiles.active");
		}
	}
}
