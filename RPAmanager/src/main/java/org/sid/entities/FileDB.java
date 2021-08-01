package org.sid.entities;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "files")
public class FileDB {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String uuid;

  private String name;

  private String type;

  @Lob
  private byte[] data;

  public FileDB() {
  }

  public FileDB(String name, String type, byte[] data) {
    this.name = name;
    this.type = type;
    this.data = data;
  }

  public Long getId() {
    return id;
  }
  
  

  public String getUuid() {
	return uuid;
}

public void setUuid(String uuid) {
	this.uuid = uuid;
}

public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public byte[] getData() {
    return data;
  }

  public void setData(byte[] data) {
    this.data = data;
  }

@Override
public String toString() {
	return "FileDB [id=" + id + ", uuid=" + uuid + ", name=" + name + ", type=" + type + ", data="
			+ Arrays.toString(data) + "]";
}
  
  

}