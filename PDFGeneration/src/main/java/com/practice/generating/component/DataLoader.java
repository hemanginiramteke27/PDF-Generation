package com.practice.generating.component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.practice.generating.entity.Employee;
import com.practice.generating.repo.EmployeeRepository;


@Component
public class DataLoader implements CommandLineRunner{
	@Autowired
	private EmployeeRepository repo;
	

	@Override
	public void run(String... args) throws Exception {
List<Employee> emp = List.of(new Employee(1, "Hemangini Ramteke", "Java Developer", (long) 100000, "A"),
		new Employee(2, "Ram", "Java Developer", (long) 90000, "A"),
		new Employee(3, "Syam", "Java Developer", (long) 85000, "A"),
		new Employee(4, "Mohit", "Java Developer", (long) 80000, "A"));
		
		repo.saveAll(emp);
		
	}
		
	}


