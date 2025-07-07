package com.Step_Up_com.Employee.service;


import com.Step_Up_com.Employee.exception.EmployeeAlreadyExistsException;
import com.Step_Up_com.Employee.exception.EmployeeNotFoundException;
import com.Step_Up_com.Employee.model.Employee;
import com.Step_Up_com.Employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;


@Service
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService {

    private final EmployeeRepository employeeRepository;


    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, String email,
                                String department, String phoneNumber, MultipartFile photo) {
        if (employeeAlreadyExists(email)) {
            throw new EmployeeAlreadyExistsException(email + " already exists!");
        }

        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setEmail(email);
        employee.setDepartment(department);
        employee.setPhoneNumber(phoneNumber);

        if (photo != null && !photo.isEmpty()) {
            try {
                employee.setImage(photo.getBytes());
            } catch (Exception e) {
                throw new RuntimeException("Failed to process photo", e);
            }
        }

        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, String firstName, String lastName, String email,
                                   String department, String phoneNumber, MultipartFile photo) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setEmail(email);
        employee.setDepartment(department);
        employee.setPhoneNumber(phoneNumber);

        if (photo != null && !photo.isEmpty()) {
            try {
                byte[] photoBytes = photo.getBytes();
                employee.setImage(photoBytes); // устанавливаем byte[] напрямую
            } catch (Exception e) {
                throw new RuntimeException("Failed to process image", e);
            }
        }

        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Sorry, no employee found with the Id :" + id));
    }

    @Override
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException("Sorry, employee not found");
        }
        employeeRepository.deleteById(id);
    }

    private boolean employeeAlreadyExists(String email) {
        return employeeRepository.findByEmail(email).isPresent();
    }

    @Override
    public byte[] getEmployeePhoto(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        return employee.getImage();
    }
}