package com.id.dynamointegration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.id.dynamointegration.model.Student;
import com.id.dynamointegration.repository.DynamoRepo;

@RestController
@RequestMapping("/dynamodb")
public class DynamoController {

	@Autowired
	private DynamoRepo repo;

	@PostMapping
	public String insertIntoDynamoDB(@RequestBody Student s){
		repo.insertIntoTable(s);
		return "Success";
	}
	
	@GetMapping
	public ResponseEntity<Student> getOneStudentDetails(@RequestParam String studentId){
		Student s = repo.getOneStudent(studentId);
		return new ResponseEntity<Student>(s,HttpStatus.OK);
	}
	
	@PutMapping
	public void update(@RequestBody Student s){
		repo.updateStudent(s);
	}
	
	@GetMapping("/printlist")
	public String print(){
		repo.getListOfTables();
		return "printed in console";
	}

	@GetMapping("/test")
	public String test(){
		repo.test();
		return "printed in console";
	}

		
}
