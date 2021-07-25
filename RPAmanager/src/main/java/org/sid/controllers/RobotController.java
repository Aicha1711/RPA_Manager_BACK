package org.sid.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.sid.entities.Robot;
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


import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class RobotController {

	@Autowired
	RobotRepository robotRepository;


//////////////*ADD a ROBOT*///////////
	@PostMapping("/robots")
	public ResponseEntity<Robot> createRobot(@RequestBody Robot robot) {
		try {
			Robot _robot = robotRepository.save
				  (new Robot(robot.getIpAddress(), 
							 robot.getPort(),
							 robot.getDomain(),
					         robot.getPsw(),
					         robot.getName(),
					         robot.getDescription(),
					         robot.getStatut()));
			return new ResponseEntity<>(_robot, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}  

	////////////// *GET ALL ROBOTS*///////////
	@GetMapping("/robots")
	public ResponseEntity<List<Robot>> getAllRobots(@RequestParam(required = false) String name) {

		try {
			List<Robot> robots = new ArrayList<Robot>();

			if (name == null)
				robotRepository.findAll().forEach(robots::add);
			else
				robotRepository.findByNameContaining(name).forEach(robots::add);

			if (robots.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(robots, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	

	////////////// *DELETE a ROBOT*///////////
	@DeleteMapping("/robots/{id}")
	public ResponseEntity<HttpStatus> deleteRobot(@PathVariable("id") long id) {

		try {
			robotRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	////////////// *UPDATE a ROBOT*///////////
	@PutMapping("/robots/{id}")
	public ResponseEntity<Robot> updateRobot(@PathVariable("id") long id, @RequestBody Robot robot) {
		Optional<Robot> robotData = robotRepository.findById(id);

		if (robotData.isPresent()) {
			Robot _robot = robotData.get();
			_robot.setIpAddress(robot.getIpAddress());
			_robot.setPort(robot.getPort());
			_robot.setDomain(robot.getDomain());
			_robot.setPsw(robot.getPsw());
			_robot.setName(robot.getName());
			_robot.setDescription(robot.getDescription());
			_robot.setStatut(robot.getStatut());
			return new ResponseEntity<>(robotRepository.save(_robot), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	
}
