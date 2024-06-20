package com.std.ec.controller;

import com.std.ec.exceptions.InvalidDataException;
import com.std.ec.model.dto.EmployeeDto;
import com.std.ec.model.entity.Employee;
import com.std.ec.service.IEmployee;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private IEmployee employeeService;

    @GetMapping("employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("employee/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @PostMapping("employee")
    public ResponseEntity<?> addEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeDto.toEmployee();
            try {
                Employee employeeCreated = employeeService.addEmployee(employee);
                return new ResponseEntity<>(employeeCreated, HttpStatus.CREATED);
            } catch (InvalidDataException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
    }

    @PutMapping("employee/{id}")
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable Long id) {
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            return new ResponseEntity<>(employeeDto, HttpStatus.NOT_FOUND);
        }
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setDocumentType(employeeDto.getDocumentType());
        employee.setDocumentNumber(employeeDto.getDocumentNumber());
        employee.setEmailAddress(employeeDto.getEmailAddress());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());
        employee.setBirthdate(employeeDto.getBirthdate());
        employee.setCellPhoneNumber(employeeDto.getCellPhoneNumber());
        employee.setId(id);
        return new ResponseEntity<>(employeeService.updateEmployee(employee), HttpStatus.OK);
    }

    @DeleteMapping("employee/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        Employee employee = employeeService.findById(id);
        employeeService.deleteEmployee(employee);
    }

    @GetMapping("employee/document")
    public Employee getEmployeeByDocument(@RequestParam String documentNumber, @RequestParam String documentType) {
        return employeeService.getEmployeeByDocument(documentNumber, documentType);
    }

    @GetMapping("employee/name")
    public Employee getEmployeeByFullName(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.getEmployeeByFullName(firstName, lastName);
    }
}
