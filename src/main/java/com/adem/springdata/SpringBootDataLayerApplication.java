package com.adem.springdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//import com.adem.springdata.entities.Provider;
import com.adem.springdata.controllers.ArticleController;

@EnableAutoConfiguration

@SpringBootApplication
public class SpringBootDataLayerApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(SpringBootDataLayerApplication.class, args);

		new File(ArticleController.uploadDirectory).mkdir();

		Path path = Paths.get(ArticleController.uploadDirectory);

		try
		{
			Files.createDirectory(path);
		}
		catch(IOException e) {}

		//Provider P1 = new Provider();
		//Provider P2 = new Provider();
	}
}
