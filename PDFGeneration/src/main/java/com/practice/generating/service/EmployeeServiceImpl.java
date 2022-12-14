package com.practice.generating.service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.generating.entity.Employee;
import com.practice.generating.repo.EmployeeRepository;


@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeRepository repo;

	@Override
	public Employee getEmpById(Integer empId) {
		Employee employee = repo.findById(empId).orElse(null);
		return employee;
	}

	@Override
	public List<Employee> getAllEmp() {
		List<Employee> findAll = repo.findAll();
		return findAll;
	}

	@Override
	public List<Employee> getByName(String empName) {
		List<Employee> findByName = repo.findByName(empName);
		return findByName;
	}

	@Override
	public void saveEmp(Employee emp) {
		repo.save(emp);
		
	}

	@Override
	public void saveAll(List<Employee> emp) {
		repo.saveAll(emp);
		
	}

	@Override
	public void deleteByEmpId(Integer empId) {
		repo.deleteById(empId);
		
	}

//	@Override
//	public void generatingPdf(List<Employee> emp) {
//		repo.
//		
//	}
}
