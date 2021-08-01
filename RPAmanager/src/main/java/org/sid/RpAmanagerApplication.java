package org.sid;





import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
	
	@Bean
	BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}
}
