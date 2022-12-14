package com.practice.generating.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.generating.entity.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	
	public List<Employee> findByName(String empName);
	
}
