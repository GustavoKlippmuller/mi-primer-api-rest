package com.std.ec.model;

import com.std.ec.model.dao.EmployeeDao;
import com.std.ec.model.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import java.time.*;
import java.util.*;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class EmployeeDaoTest {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final Random RANDOM = new Random();

    @Autowired
    private EmployeeDao employeeDao;

    @Test
    public void testSaveEmployee() {
        Employee employee = this.getEmployee();
        Employee savedEmployee = this.employeeDao.save(employee);
        assertThat(savedEmployee.getId()).isNotNull();
    }

    @Test
    public void testUpdateEmployee() {
        String cellPhoneNumber = "123456789";
        Employee employee = this.getEmployee();
        Employee savedEmployee = this.employeeDao.save(employee);
        savedEmployee.setCellPhoneNumber(cellPhoneNumber);
        Employee updatedEmployee = this.employeeDao.save(savedEmployee);
        assertThat(updatedEmployee.getId()).isNotNull();
        assertThat(updatedEmployee.getCellPhoneNumber()).isEqualTo(cellPhoneNumber);
    }

    @Test
    public void testDeleteEmployee() {
        Employee employee = this.getEmployee();
        Employee savedEmployee = this.employeeDao.save(employee);
        this.employeeDao.delete(savedEmployee);
        Employee deletedEmployee = this.employeeDao.findById(savedEmployee.getId()).orElse(null);
        assertThat(deletedEmployee).isNull();
    }

    @Test
    public void testFindEmployeeById() {
        Employee employee = this.getEmployee();
        Employee savedEmployee = this.employeeDao.save(employee);
        Optional<Employee> employeeOptional = this.employeeDao.findById(savedEmployee.getId());
        assertThat(employeeOptional.isPresent()).isTrue();
        assertThat(employeeOptional.get().getId()).isEqualTo(savedEmployee.getId());
        assertThat(employeeOptional.get().getFirstName()).isEqualTo(savedEmployee.getFirstName());
    }

    @Test
    public void testFindAllEmployees() {
        Employee employee = this.getEmployee();
        this.employeeDao.save(employee);
        employee.setDocumentNumber("12345679");
        this.employeeDao.save(employee);
        List<Employee> employees = this.employeeDao.findAll();
        assertThat(employees.size()).isEqualTo(2);
    }

    @Test
    public void testFindEmployeeByFullName() {
        Employee employee = this.getEmployee();
        Employee employeeSaved = this.employeeDao.save(employee);
        Optional<Employee> employeeOptional = employeeDao.findEmployeeByFirstNameAndLastName(employee.getFirstName(), employee.getLastName());
        assertThat(employeeOptional.isPresent()).isTrue();
        assertThat(employeeOptional.get().getId()).isEqualTo(employeeSaved.getId());
        assertThat(employeeOptional.get().getFirstName()).isEqualTo(employeeSaved.getFirstName());
    }

    @Test
    public void testFindEmployeeByDocumentNumber() {
        Employee employee = this.getEmployee();
        Employee employeeSaved = this.employeeDao.save(employee);
        Optional<Employee> employeeOptional = employeeDao.findEmployeeByDocumentNumberAndDocumentType(employee.getDocumentNumber(), employee.getDocumentType());
        assertThat(employeeOptional.isPresent()).isTrue();
        assertThat(employeeOptional.get().getId()).isEqualTo(employeeSaved.getId());
        assertThat(employeeOptional.get().getFirstName()).isEqualTo(employeeSaved.getFirstName());
    }

    @Test
    public void testFirstNameIsNull() {
        Employee employee = this.getEmployee();
        employee.setFirstName(null);
        this.validateSaveValues(employee);
    }

    @Test
    public void testLastNameIsNull() {
        Employee employee = this.getEmployee();
        employee.setLastName(null);
        this.validateSaveValues(employee);
    }

    @Test
    public void testBirthdateIsNull() {
        Employee employee = this.getEmployee();
        employee.setBirthdate(null);
        this.validateSaveValues(employee);
    }

    @Test
    public void testDocumentTypeIsNull() {
        Employee employee = this.getEmployee();
        employee.setDocumentType(null);
        this.validateSaveValues(employee);
    }

    @Test
    public void testDocumentNumberIsNull() {
        Employee employee = this.getEmployee();
        employee.setDocumentNumber(null);
        this.validateSaveValues(employee);
    }

    @Test
    public void testEmailIsNull() {
        Employee employee = this.getEmployee();
        employee.setEmailAddress(null);
        this.validateSaveValues(employee);
    }

    @Test
    public void testMaxLengthFirstName() {
        Employee employee = this.getEmployee();
        employee.setFirstName(generateRandomString(51));
        validateSaveValues(employee);
    }

    @Test
    public void testMaxLengthLasttName() {
        Employee employee = this.getEmployee();
        employee.setLastName(generateRandomString(51));
        validateSaveValues(employee);
    }

    @Test
    public void testMaxLengthDocumentType() {
        Employee employee = this.getEmployee();
        employee.setDocumentType(generateRandomString(4));
        validateSaveValues(employee);
    }

    @Test
    public void testMaxLengthDocumentNumber() {
        Employee employee = this.getEmployee();
        employee.setDocumentNumber(generateRandomString(9));
        validateSaveValues(employee);
    }

    @Test
    public void testMaxLengthPhoneNumber() {
        Employee employee = this.getEmployee();
        employee.setPhoneNumber(generateRandomString(15));
        validateSaveValues(employee);
    }

    @Test
    public void testMaxLengthCellPhoneNumber() {
        Employee employee = this.getEmployee();
        employee.setCellPhoneNumber(generateRandomString(15));
        validateSaveValues(employee);
    }

    @Test
    public void testMaxLengthEmailAddress() {
        Employee employee = this.getEmployee();
        employee.setEmailAddress(generateRandomString(76));
        validateSaveValues(employee);
    }

    private void validateSaveValues(Employee employee) {
        try {
            this.employeeDao.save(employee);
            throw new Exception();
        } catch (DataIntegrityViolationException e) {
            assertThat(e).isInstanceOf(DataIntegrityViolationException.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Employee getEmployee() {
        return new Employee(
                0L,
                "Meliodas",
                "Dragon de la Ira",
                Date.from((LocalDate.of(1985, 5, 15)).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                "DNI",
                "12345678",
                "",
                "+541112345678",
                "meliodas.dragon@example.com",
                LocalDateTime.now()
        );
    }

    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }

        return sb.toString();
    }
}
