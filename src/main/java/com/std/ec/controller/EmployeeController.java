package com.std.ec.controller;

import com.std.ec.model.entity.Employee;
import com.std.ec.service.IEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private IEmployee employeeService;

    @GetMapping("emplotees")
    public List<Employee> getAllEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("employee/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @PostMapping("employee")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @PutMapping("employee")
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("employee/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        Employee employee = employeeService.findById(id);
        employeeService.deleteEmployee(employee);
    }
}
