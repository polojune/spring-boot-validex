package com.cos.validex01;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/board")
public class ProjectTaskController {
   
	   @Autowired
	   private ProjectTaskRepository projectTaskRepository; 
	   
	   @PostMapping({"","/"})
	   public ResponseEntity<?> save(@Valid @RequestBody ProjectTask requestProjectTask, BindingResult bindingResult){
		
	  
		   
	
		   
		ProjectTask entityProjectTask  = projectTaskRepository.save(requestProjectTask);
		
		  return new ResponseEntity<ProjectTask>(entityProjectTask,HttpStatus.CREATED); 
	   }
	
}
