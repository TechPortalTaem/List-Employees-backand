package com.Step_Up_com.Employee.repository;


import com.Step_Up_com.Employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;



public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);
}
