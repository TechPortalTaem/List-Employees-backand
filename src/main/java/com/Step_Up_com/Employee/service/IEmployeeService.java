package com.Step_Up_com.Employee.service;

import com.Step_Up_com.Employee.model.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IEmployeeService {


    List<Employee> getEmployees();

    Employee updateEmployee(Long id, String firstName, String lastName, String email, String department, String phoneNumber, MultipartFile photo);

    Employee getEmployeeById(Long id);

    void deleteEmployee(Long id);

    Employee addEmployee(String firstName, String lastName, String email,
                         String department, String phoneNumber, MultipartFile photo);

    byte[] getEmployeePhoto(Long id);
}