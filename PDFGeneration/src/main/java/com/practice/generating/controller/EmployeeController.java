package com.practice.generating.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.generating.entity.Employee;
import com.practice.generating.export.PDFGenerator;
import com.practice.generating.service.EmployeeService;



@RestController
@RequestMapping("/emp1")

public class EmployeeController {
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	private EmployeeService service;
	@Autowired
	private PDFGenerator export;

	@GetMapping("/empid/{id}")
	public ResponseEntity<Employee> getEmpById(@PathVariable Integer empId) {
		LOG.info("getEmpById is  called now");
		Employee empById = service.getEmpById(empId);
		if(empById!= null) {
			LOG.info("getEmpById is executed now");
			return ResponseEntity.ok(empById);
		}else {
			LOG.error("Employee with ID is not found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getall")
	public List<Employee> getAllEmp() {
		List<Employee> allEmp = service.getAllEmp();
		return allEmp;
	}

	@GetMapping("/empName")
	public List<Employee> getByName(@PathVariable String empName) {
		List<Employee> byName = service.getByName(empName);
		return byName;
	}

	@PostMapping("/save")
	public void saveEmp(Employee emp) {
		service.saveEmp(emp);
	}

	@PostMapping("/saveall")
	public void saveAll(List<Employee> emp) {
		service.saveAll(emp);
	}

	@DeleteMapping("/empid/{id}")
	public void deleteByEmpId(@PathVariable Integer empId) {
		service.deleteByEmpId(empId);
	}
	
	@GetMapping("/exportpdf")
	public void exportToPDF(HttpServletResponse response) throws IOException{
		response.setContentType("application/pdf");
		export.generate(response);
	}
	
	@GetMapping("/export/pdf")
	public void exportToPdf(HttpServletResponse response) throws IOException {
		
		response.setContentType("application/pdf");

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");

		String string = df.format(new Date());

		String headerKey = "Content-Disposition";

		String headerValue = "attachment; filename=employee" + string + ".pdf";
		response.setHeader(headerKey, headerValue);
		
		List<Employee> list = service.getAllEmp();
		PDFGenerator pg = new PDFGenerator(list);
pg.generate(response);
	}
}
