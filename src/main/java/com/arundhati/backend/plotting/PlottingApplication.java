package com.arundhati.backend.plotting;

import com.arundhati.backend.plotting.config.security.AppJwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(AppJwtProperties.class)
@ServletComponentScan
public class PlottingApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlottingApplication.class, args);
	}

}
