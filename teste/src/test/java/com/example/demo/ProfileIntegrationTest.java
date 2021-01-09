package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.config.EnvironmentConfig;

@SpringBootTest
@ActiveProfiles("test")
//@ActiveProfiles("test2")
public class ProfileIntegrationTest {

	@Autowired
	private EnvironmentConfig environmentConfig;
	
	@org.junit.Test
	public void test() {
		environmentConfig.someMethod();
	}
}
