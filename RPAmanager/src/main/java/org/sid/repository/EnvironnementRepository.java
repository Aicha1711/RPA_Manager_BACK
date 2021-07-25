package org.sid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import org.sid.entities.*;

@Repository
public interface EnvironnementRepository extends JpaRepository<Environnement, Long> {



}
