package com.apitore.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.apitore.config.ConfigServerMain;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ConfigServerMain.class)
public class SpringConfigServerApplicationTests {

	@Test
	public void contextLoads() {
	}

}
