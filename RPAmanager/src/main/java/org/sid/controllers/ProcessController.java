package org.sid.controllers;

import java.util.List;
import java.util.Optional;

import org.sid.entities.Process;
import org.sid.repository.ProcessRepository;
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
public class ProcessController {

	@Autowired
	ProcessRepository processRepository;

//////////////*ADD a Process*///////////
	@PostMapping("/process")
	public ResponseEntity<Process> createProcess(@RequestBody Process process) {
		try {
			Process _process = new Process();
			_process.setName(process.getName());
			_process.setDescription(process.getDescription());
			_process.setPriority(process.getPriority());
			_process.setEnvironnement(process.getEnvironnement());
		

			processRepository.save(_process);
			return new ResponseEntity<>(_process, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//////////////*GET ALL Process *///////////
	@GetMapping("/process")
	public List<Process> getAllProcess() {

		return processRepository.findAll();

	}

//////////////*DELETE a process*///////////
	@DeleteMapping("/process/{id}")
	public ResponseEntity<HttpStatus> deleteProcess(@PathVariable("id") long id) {

		try {
			processRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//////////////*UPDATE a process*///////////
	@PutMapping("/process/{id}")
	public ResponseEntity<Process> updateProcess(@PathVariable("id") long id, @RequestBody Process process) {
		Optional<Process> processData = processRepository.findById(id);

		if (processData.isPresent()) {
			Process _process = processData.get();
			_process.setName(process.getName());
			_process.setDescription(process.getDescription());
			_process.setPriority(process.getPriority());
			_process.setEnvironnement(process.getEnvironnement());
			return new ResponseEntity<>(processRepository.save(_process), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
