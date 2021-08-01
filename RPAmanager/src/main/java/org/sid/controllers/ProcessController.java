package org.sid.controllers;

import java.util.List;
import java.util.Optional;

import org.sid.entities.Environnement;
import org.sid.entities.FileDB;
import org.sid.entities.Process;
import org.sid.repository.EnvironnementRepository;
import org.sid.repository.FileDBRepository;
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
	@Autowired
	EnvironnementRepository environnementRepository;
	@Autowired
	FileDBRepository fileDBRepository;
	
	
	@PostMapping("/process")
	public ResponseEntity<Process> createProcess(@RequestBody FormProcess form) {
		try {
			Environnement environnement=environnementRepository.findById(form.environnements_id).get();
			FileDB file = fileDBRepository.getByName(form.getFileName());
			
			Process _process = new Process();
			_process.setName(form.getName());
			_process.setDescription(form.getDescription());
			_process.setPriority(form.isPriority());
			
			_process.setEnvironnement(environnement);
			_process.setFile(file);
			processRepository.save(_process);
			 
			return new ResponseEntity<>(_process, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//////////////*ADD a Process*///////////
	/*@PostMapping("/process")
	public ResponseEntity<Process> createProcess(@RequestBody Process process) {
		try {
			
			Process _process = new Process();
			_process.setName(process.getName());
			_process.setDescription(process.getDescription());
			_process.setPriority(process.getPriority());
			_process.setEnvironnement(process.getEnvironnement());
			_process.setFile(process.getFile());
			processRepository.save(_process);
			 
			return new ResponseEntity<>(_process, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}*/

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
			_process.setFile(process.getFile());
			return new ResponseEntity<>(processRepository.save(_process), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}

class FormProcess{
	
	String name;
	String description;
	boolean priority;
	 Long environnements_id;
	 String fileName;
	 
	
	 
	 
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Long getEnvironnements_id() {
		return environnements_id;
	}
	public void setEnvironnements_id(Long environnements_id) {
		this.environnements_id = environnements_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isPriority() {
		return priority;
	}
	public void setPriority(boolean priority) {
		this.priority = priority;
	}
	@Override
	public String toString() {
		return "FormProcess [name=" + name + ", description=" + description + ", priority=" + priority
				+ ", environnements_id=" + environnements_id + ", fileName=" + fileName + "]";
	}
	
	
	
	
	
	
}
