package com.std.ec.service.impl;

import com.std.ec.exceptions.InvalidDataException;
import com.std.ec.model.dao.EmployeeDao;
import com.std.ec.model.entity.Employee;
import com.std.ec.model.enums.DocumentTypes;
import com.std.ec.service.IEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

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
        employee.setFirstName(employee.getFirstName().toUpperCase());
        employee.setLastName(employee.getLastName().toUpperCase());
        if(!isValidEmail(employee.getEmailAddress())) throw new InvalidDataException("Invalid email address");
        if(!isValidDocumentType(employee.getDocumentType())) throw new InvalidDataException("Invalid document type");
        if(!isValidDocumentNumber(employee.getDocumentNumber())) throw new InvalidDataException("Invalid document number");
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

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        if (email == null || email.isEmpty()) {
            return false;
        }
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidDocumentType(String documentType) {
        try {
            DocumentTypes.valueOf(documentType);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private boolean isValidDocumentNumber(String documentNumber) {
        String regex = "^[0-9]{6,8}$";
        return documentNumber != null && documentNumber.matches(regex);
    }

}
