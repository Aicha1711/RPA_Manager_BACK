package org.sid.repository;

import org.sid.entities.AppUser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long>{
	AppUser findByEmail(String email);

}
