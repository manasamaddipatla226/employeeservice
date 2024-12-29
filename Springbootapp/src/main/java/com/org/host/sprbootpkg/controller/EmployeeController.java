package com.org.host.sprbootpkg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.host.sprbootpkg.model.EmployeeDAO;
import com.org.host.sprbootpkg.request.EmployeeRequest;
import com.org.host.sprbootpkg.response.MainResponse;
import com.org.host.sprbootpkg.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	public EmployeeController() {
		// TODO Auto-generated constructor stub
	}

	// create api

	@PostMapping("/createEmp")
	public EmployeeDAO createEmployee(@RequestBody EmployeeRequest employeeRequest) {
		EmployeeDAO empResp = employeeService.createEmployee(employeeRequest);
		return empResp;
	}

	@GetMapping("/getEmp/{id}")
	public EmployeeDAO getEmployee(@PathVariable(name = "id") String id) {
		EmployeeDAO empResp = employeeService.getEmployee(id);
		return empResp;
	}

	@PutMapping("/editEmp/{id}")
	public EmployeeDAO editEmployee(@PathVariable(name = "id") String id,
			@RequestBody EmployeeRequest employeeRequest) {
		EmployeeDAO empResp = employeeService.editEmployee(id, employeeRequest);
		return empResp;
	}

	@DeleteMapping("/deleteEmp/{id}")
	public String deleteEmployee(@PathVariable(name = "id") String id) {
		String empResp = employeeService.deleteEmployee(id);
		return empResp;
	}

//	microservice call

	@GetMapping("/getEmpDeptDetails/{empId}")
	public MainResponse getEmployeeAndDeptDetails(@PathVariable(name = "empId") String id) {
		MainResponse empDeptResp = employeeService.getEmployeeAndDeptDetails(id);
		return empDeptResp;
	}

}
