package org.sid.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.sid.entities.FileDB;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, Long> {
	@Query(value="select * from files f where f.name = ?1", nativeQuery = true)
	FileDB getByName(String name);

}