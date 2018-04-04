package com.denver.intl.airport;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=DenverAirportApplication.class)
@ActiveProfiles("test")
public class SpringDenverAirportApplicationTests {

	@Test
	public void testContextLoads() throws Exception {
	}

}
