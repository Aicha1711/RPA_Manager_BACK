package org.sid.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "processes ")
public class Process {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "Name")
	private String name;

	@Column(name = "Description")
	private String description;
	
	@Column(name = "Priority")
	private Boolean priority;

	@ManyToOne
	private Environnement environnement;
	//@JoinColumn(name="env_id", referencedColumnName="id")
	
	@ManyToOne
	private  FileDB file;

	public FileDB getFile() {
		return file;
	}

	public void setFile(FileDB file) {
		this.file = file;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Boolean getPriority() {
		return priority;
	}

	public void setPriority(Boolean priority) {
		this.priority = priority;
	}

	public Environnement getEnvironnement() {
		return environnement;
	}

	public void setEnvironnement(Environnement environnement) {
		this.environnement = environnement;
	}

	

	public Process() {
		
	}

	public Process(String name, String description, Boolean priority, Environnement environnement, FileDB file) {
		super();
		this.name = name;
		this.description = description;
		this.priority = priority;
		this.environnement = environnement;
		this.file = file;
	}

	
	

	
	
	

}