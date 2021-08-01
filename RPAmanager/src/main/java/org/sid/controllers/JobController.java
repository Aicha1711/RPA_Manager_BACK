package org.sid.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.sid.repository.FileDBRepository;
import org.sid.repository.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@CrossOrigin("http://localhost:4200")
public class JobController {
	@Autowired
	ProcessRepository processRepository;
	
	@GetMapping("/jobs/{id}")
	//public String runProcess(@RequestParam String processName) {
	public String runProcess(@PathVariable Long id) {
	
		 try {
			 String fileName = processRepository.findById(id).get().getFile().getName();
			// Process process = Runtime.getRuntime().exec("cmd /c UiRobot execute --file 'C:\\Users\\Aicha\\Documents\\UiPath\\Task1\\Task1.1.0.1.nupkg' " ); 
			 Process process = Runtime.getRuntime().exec("cmd /c UiRobot execute --file 'C:\\Users\\Aicha\\Documents\\UiPath\\Task1\\"+fileName+"'" ); 
			 StringBuilder output = new StringBuilder(); 
    		 BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream())); 
    		 String line;
 			
    		 while((line = reader.readLine()) != null) {
 				output.append(line + "\n");}
 			
 				int exitVal = process.waitFor();
 				if (exitVal == 0) {
 					System.out.println("Success");
 					return "Success";
 				} else {
 					System.out.println("Something abnormal has haapened :( ");
 					return "Something went wrong!";
 			}

    	 
    	 }catch (IOException e) {
 			return e.getMessage();
    	 }catch (InterruptedException e) {
 			return e.getMessage();
 		}
	}

}
