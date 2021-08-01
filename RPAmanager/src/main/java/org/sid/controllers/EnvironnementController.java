package org.sid.controllers;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.sid.entities.Environnement;
import org.sid.entities.Robot;
import org.sid.repository.EnvironnementRepository;
import org.sid.repository.RobotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController

public class EnvironnementController {

	@Autowired
	EnvironnementRepository environnementRepository;
	@Autowired
	RobotRepository robotRepository;

//////////////*ADD a Environment*///////////
	@PostMapping("/environnements")
	public ResponseEntity<Environnement> createEnvironnement(@RequestBody FormEnv form) {
		try {
			
			Set<Robot> robots=  new HashSet<>();
			form.getRobot_id().forEach(rid->{
				Robot robot = robotRepository.findById(rid).get();
				robots.add(robot);
				
			});
			Environnement _environnement = new Environnement();
			_environnement.setName(form.getName());
			_environnement.setDescription(form.getDescription());
			
			_environnement.setRobots(robots);
			
			environnementRepository.save(_environnement);
			
		
			//_environnement.setRobots(environnement.getRobots());
				System.out.println("-------------------");
				System.out.println(form.toString());
				System.out.println("-------------------");
			//;

			return new ResponseEntity<>(_environnement, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//////////////*GET ALL ENVIRONNEMENT *///////////
	@GetMapping("/environnements")
	public List<Environnement> getAllEnvironnements() {

		return environnementRepository.findAll();

	}

	////////////// *DELETE a ENVIRONNEMENT*///////////
	@DeleteMapping("/environnements/{id}")
	public ResponseEntity<HttpStatus> deleteEnvironnement(@PathVariable("id") long id) {

		try {
			environnementRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	////////////// *UPDATE a Environment*///////////
	@PutMapping("/environnements/{id}")
	public ResponseEntity<Environnement> updateEnvironnement(@PathVariable("id") long id,
			@RequestBody Environnement environnement) {
		Optional<Environnement> environnementData = environnementRepository.findById(id);

		if (environnementData.isPresent()) {
			Environnement _environnement = environnementData.get();
			_environnement.setName(environnement.getName());
			_environnement.setDescription(environnement.getDescription());
			_environnement.setRobots(environnement.getRobots());
			return new ResponseEntity<>(environnementRepository.save(_environnement), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}

class FormEnv{
	String description;
	String name;
	List<Long> robot_id;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Long> getRobot_id() {
		return robot_id;
	}
	public void setRobot_id(List<Long> robot_id) {
		this.robot_id = robot_id;
	}
	@Override
	public String toString() {
		return "FormEnv [description=" + description + ", name=" + name + ", robot_id=" + robot_id + "]";
	}
	
	
}



