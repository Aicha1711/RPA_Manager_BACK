package org.sid.repository;




import java.util.ArrayList;

import org.sid.entities.Robot;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface RobotRepository extends JpaRepository<Robot, Long> {

	

	ArrayList<Robot> findByNameContaining(String name);



	


}
