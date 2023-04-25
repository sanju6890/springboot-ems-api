package com.samsung.training.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samsung.training.ems.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
