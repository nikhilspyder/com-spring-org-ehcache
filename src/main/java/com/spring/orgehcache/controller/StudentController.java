package com.spring.orgehcache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.orgehcache.domain.Student;
import com.spring.orgehcache.service.StudentService;

@RestController
@RequestMapping(path = "/v1")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping(path = "/check", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Student getDetails(@RequestHeader String transactionId, @RequestBody Student student) {

		return studentService.getDetails(student);
	}

}