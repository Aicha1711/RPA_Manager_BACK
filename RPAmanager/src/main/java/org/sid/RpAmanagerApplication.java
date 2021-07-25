package org.sid;


import javax.annotation.Resource;

import org.sid.entities.Environnement;
import org.sid.entities.Robot;
import org.sid.repository.RobotRepository;
import org.sid.service.FileStaticStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RpAmanagerApplication implements CommandLineRunner {

	// @Resource
	// FileStaticStorage storagestaticService;
	public static void main(String[] args) {
		SpringApplication.run(RpAmanagerApplication.class, args);
	}
 

	@Override
	public void run(String... args) throws Exception {

		
	

		// add robots 1 and 2 to environment
		// environnement.getRobots().add(robot1);
//		
	  // this.environnementRepository.save(environnement);

		// storageService.deleteAll();
		//storagestaticService.loadAll();

	}
}
