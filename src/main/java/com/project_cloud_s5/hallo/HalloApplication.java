package com.project_cloud_s5.hallo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.project_cloud_s5.hallo.model.proprietaire.Proprietaire;

@SpringBootApplication
public class HalloApplication {

	public static void main(String[] args) {
		SpringApplication.run(HalloApplication.class, args);
		System.out.println("feno");
	}

}
