package com.adem.springdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import com.adem.springdata.entities.Provider;
//import com.adem.springdata.repositories.ProviderRepository;

@EnableAutoConfiguration

@SpringBootApplication
public class SpringBootDataLayerApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(SpringBootDataLayerApplication.class, args);
		
		//Provider P1 = new Provider();
		//Provider P2 = new Provider();
	}
}
