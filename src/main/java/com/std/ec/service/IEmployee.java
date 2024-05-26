package com.std.ec.service;

import com.std.ec.model.entity.Employee;

import java.util.List;

public interface IEmployee {

    Employee findById(Long id);
    List<Employee> getEmployees();
    Employee addEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
    void deleteEmployee(Employee employee);
}
