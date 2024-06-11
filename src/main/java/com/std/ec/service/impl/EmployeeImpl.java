package com.std.ec.service.impl;

import com.std.ec.model.dao.EmployeeDao;
import com.std.ec.model.entity.Employee;
import com.std.ec.service.IEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeImpl implements IEmployee {

    @Autowired
    private EmployeeDao employeeDao;

    @Transactional(readOnly = true)
    @Override
    public Employee findById(Long id) {
        return employeeDao.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Employee> getEmployees() {
        return employeeDao.findAll();
    }

    @Transactional
    @Override
    public Employee addEmployee(Employee employee) {
        employee.setDocumentType(employee.getDocumentType().toUpperCase());
        return employeeDao.save(employee);
    }

    @Transactional
    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeDao.save(employee);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        employeeDao.delete(employee);
    }

    @Override
    public Employee getEmployeeByFullName(String firstName, String lastName) {
        return employeeDao.findEmployeeByFirstNameAndLastName(firstName, lastName).orElse(null);
    }

    @Override
    public Employee getEmployeeByDocument(String documentNumber, String documentType) {
        documentType = documentType.toUpperCase();
        return employeeDao.findEmployeeByDocumentNumberAndDocumentType(documentNumber, documentType).orElse(null);
    }
}
