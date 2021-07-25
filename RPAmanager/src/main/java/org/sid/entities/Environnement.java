package org.sid.entities;

import java.util.*;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import javax.persistence.Table;


@Entity
@Table(name = "environnements ")
public class Environnement {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "Name")
	private String name;

	@Column(name = "Description")
	private String description;
	


	@ManyToMany
	@JoinTable(name = "Environment_robot", 
joinColumns = @JoinColumn(name = "Environnment_id",referencedColumnName = "id") , 
inverseJoinColumns = { @JoinColumn(name = "robot_id", referencedColumnName = "id") })
    private Set<Robot> robots = new HashSet<>();



	public Environnement(String name, String description, Set<Robot> robots) {
	super();
	this.name = name;
	this.description = description;
	this.robots = robots;
}
	public Environnement(){
		
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


	public Set<Robot> getRobots() {
		return robots;
	}


	public void setRobots(Set<Robot> robots) {
		this.robots = robots;
	}




	
}
