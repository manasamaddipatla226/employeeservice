package com.org.host.sprbootpkg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.host.sprbootpkg.model.EmployeeDAO;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDAO, String> {

}
