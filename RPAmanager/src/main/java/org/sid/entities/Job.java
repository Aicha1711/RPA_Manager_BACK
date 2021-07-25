package org.sid.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "jobs")
public class Job {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "Name")
	private String name;

	
	
	@Column(name = "Priority")
	private Boolean priority;

	@ManyToOne
	private Environnement environnement;

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

	public Job() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Job(String name, String description, Boolean priority, Environnement environnement) {
		super();
		this.name = name;
		this.priority = priority;
		this.environnement = environnement;
	}

}
