package com.org.host.sprbootpkg.response;

import com.org.host.sprbootpkg.model.EmployeeDAO;

public class EmpDeptResponse {

	private EmployeeDAO employeeResp;

	private DepartmentResponse departmentResp;

	public EmployeeDAO getEmployeeResp() {
		return employeeResp;
	}

	public void setEmployeeResp(EmployeeDAO employeeResp) {
		this.employeeResp = employeeResp;
	}

	public DepartmentResponse getDepartmentResp() {
		return departmentResp;
	}

	public void setDepartmentResp(DepartmentResponse departmentResp) {
		this.departmentResp = departmentResp;
	}

	
}
