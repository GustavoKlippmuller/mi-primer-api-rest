package com.std.ec.model.dto;

import com.std.ec.model.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class EmployeeDto {
    private String firstName;
    private String lastName;
    private Date birthdate;
    private String documentType;
    private String documentNumber;
    private String phoneNumber;
    private String cellPhoneNumber;
    private String emailAddress;

    public Employee toEmployee() {
        Employee employee = new Employee();
        employee.setFirstName(this.firstName);
        employee.setLastName(this.lastName);
        employee.setBirthdate(this.birthdate);
        employee.setDocumentType(this.documentType);
        employee.setDocumentNumber(this.documentNumber);
        employee.setPhoneNumber(this.phoneNumber);
        employee.setCellPhoneNumber(this.cellPhoneNumber);
        employee.setEmailAddress(this.emailAddress);
        return employee;
    }
}
