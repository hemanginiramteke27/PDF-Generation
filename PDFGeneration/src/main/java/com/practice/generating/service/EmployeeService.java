package com.practice.generating.service;



import java.util.List;

import org.springframework.stereotype.Service;

import com.practice.generating.entity.Employee;

@Service
public interface EmployeeService {

	public Employee getEmpById(Integer empId);
	public List<Employee> getAllEmp();
	public List<Employee> getByName(String empName);
	
	public void saveEmp(Employee emp);
    public void saveAll(List<Employee> emp);
    public void deleteByEmpId(Integer empId);
  //  public void generatingPdf(List<Employee> emp);
    
    
}
