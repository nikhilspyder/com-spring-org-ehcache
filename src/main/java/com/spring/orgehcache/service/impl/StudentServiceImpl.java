package com.spring.orgehcache.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.spring.orgehcache.domain.Student;
import com.spring.orgehcache.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{

	@Cacheable(value = "cachingStudent" , key = "#student.getPhoneNumber()")
	public Student getDetails(Student student) {
		
		System.out.println("Inside getDetails");
		
		student.setUserName("PES-" + student.getUserName());
		return student;
	}

}
