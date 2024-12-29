package com.org.host.sprbootpkg.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.host.sprbootpkg.model.EmployeeDAO;
import com.org.host.sprbootpkg.repository.EmployeeRepository;
import com.org.host.sprbootpkg.request.EmployeeRequest;
import com.org.host.sprbootpkg.response.DepartmentResponse;
import com.org.host.sprbootpkg.response.EmpDeptResponse;
import com.org.host.sprbootpkg.response.MainResponse;

@Service

public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private RestTemplate restTemplate;

	public EmployeeService() {
		// TODO Auto-generated constructor stub
	}

	public EmployeeDAO createEmployee(EmployeeRequest employeeRequest) {

		EmployeeDAO employeeDAO = new EmployeeDAO();
		employeeDAO.setId(employeeRequest.getId());
		employeeDAO.setName(employeeRequest.getName());
		employeeDAO.setEmail(employeeRequest.getEmail());

		EmployeeDAO empResponse = employeeRepository.save(employeeDAO);
		return empResponse;
	}

	public EmployeeDAO getEmployee(String id) {
		// TODO Auto-generated method stub
		Optional<EmployeeDAO> empResp = employeeRepository.findById(id);

		return empResp.get();
	}

	public EmployeeDAO editEmployee(String id, EmployeeRequest employeeRequest) {
		// TODO Auto-generated method stub
		EmployeeDAO employee = getEmployee(id);
		employee.setEmail(employeeRequest.getEmail());
		employee.setName(employeeRequest.getName());
		EmployeeDAO empResp = employeeRepository.save(employee);
		return empResp;
	}

	public String deleteEmployee(String id) {
		// TODO Auto-generated method stub
		EmployeeDAO employee = getEmployee(id);
		employeeRepository.delete(employee);
		return "Deleted Successfully";
	}

	public MainResponse getEmployeeAndDeptDetails(String id) {
		// TODO Auto-generated method stub
		EmployeeDAO employeeResp = getEmployee(id);
		String deptId = employeeResp.getDeptId();

		EmpDeptResponse empDeptResponse = new EmpDeptResponse();
		empDeptResponse.setEmployeeResp(employeeResp);

		// microservice call using resttemplate
		String url = "http://localhost:8086/api/getdept/" + deptId;
		ResponseEntity<MainResponse> mainDeptEntityResponse = restTemplate.exchange(url, HttpMethod.GET, null,
				MainResponse.class);
		MainResponse mainDeptResponse = mainDeptEntityResponse.getBody();
		if (mainDeptResponse != null && mainDeptResponse.getMessage().equalsIgnoreCase("Success")) {
			ObjectMapper objectMapper = new ObjectMapper();
			DepartmentResponse deptResponse = objectMapper.convertValue(mainDeptResponse.getData(), DepartmentResponse.class);
			empDeptResponse.setDepartmentResp(deptResponse);
		}
		MainResponse mainResponse = new MainResponse();
		mainResponse.setMessage("Success");
		mainResponse.setData(empDeptResponse);
		return mainResponse;
	}

}
