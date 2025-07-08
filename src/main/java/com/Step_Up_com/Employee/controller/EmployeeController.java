package com.Step_Up_com.Employee.controller;

import com.Step_Up_com.Employee.model.Employee;
import com.Step_Up_com.Employee.service.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;


@CrossOrigin(origins = "https://list-employees-frontand.vercel.app")
@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final IEmployeeService employeeService;
    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees(){
        return new ResponseEntity<>(employeeService.getEmployees(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> addEmployee(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("department") String department,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam(value = "photo", required = false) MultipartFile photo
    ) {
        Employee saved = employeeService.addEmployee(firstName, lastName, email, department, phoneNumber, photo);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("department") String department,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam(value = "photo", required = false) MultipartFile photo
    ) {
        Employee updatedEmployee = employeeService.updateEmployee(id, firstName, lastName, email, department, phoneNumber, photo);
        return ResponseEntity.ok(updatedEmployee);
    }



    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/employee/{id}")
    public Employee getEmployeeById(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }


    @GetMapping("/employee/{id}/photo")
    public ResponseEntity<byte[]> getEmployeePhoto(@PathVariable Long id) {
        byte[] image = employeeService.getEmployeePhoto(id);

        if (image == null || image.length == 0) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity
                .ok()
                .header("Content-Type", "image/jpeg")
                .body(image);
    }

}
