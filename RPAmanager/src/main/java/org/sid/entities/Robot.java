package org.sid.entities;



import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "robots")
public class Robot {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "IpAddress")
	private String ipAddress;

	@Column(name = "Port")
	private int port;

	@Column(name = "Domain")
	private String domain;

	@Column(name = "Password")
	private String psw;

	@Column(name = "Name")
	private String name;

	@Column(name = "Description")
	private String description;

	@Column(name = "Statut")
	private Boolean statut;

	@ManyToMany(mappedBy = "robots")
   private List<Environnement> environnements;
	
	



	public Robot(String ipAddress, int port, String domain, String psw, String name, String description,
			Boolean statut) {
		super();
		
		this.ipAddress = ipAddress;
		this.port = port;
		this.domain = domain;
		this.psw = psw;
		this.name = name;
		this.description = description;
		this.statut = statut;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getIpAddress() {
		return ipAddress;
	}



	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}



	public int getPort() {
		return port;
	}



	public void setPort(int port) {
		this.port = port;
	}



	public String getDomain() {
		return domain;
	}



	public void setDomain(String domain) {
		this.domain = domain;
	}



	public String getPsw() {
		return psw;
	}



	public void setPsw(String psw) {
		this.psw = psw;
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



	public Boolean getStatut() {
		return statut;
	}



	public void setStatut(Boolean statut) {
		this.statut = statut;
	}



	public Robot() {
	
	}





}
