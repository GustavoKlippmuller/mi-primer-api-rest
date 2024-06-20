package com.std.ec.service;

import com.std.ec.model.dao.EmployeeDao;
import com.std.ec.model.entity.Employee;
import com.std.ec.service.impl.EmployeeImpl;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EmployeeTest {

    @InjectMocks
    private EmployeeImpl service;

    @Mock
    private EmployeeDao repository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllEmployees() {
        when(repository.findAll()).thenReturn(Arrays.asList(
                getEmployee("123456789"),
                getEmployee("987654321")
        ));

        List<Employee> employees = service.getEmployees();

        assertEquals(2, employees.size());
        Employee employee = employees.get(0);
        assertEquals("Meliodas", employee.getFirstName());
        assertEquals("Dragon de la Ira", employee.getLastName());
    }

    @Test
    public void testGetEmployeeById() {
        when(repository.findById(1L)).thenReturn(Optional.of(getEmployee("123456789")));

        Employee foundEmployee = service.findById(1L);

        assertNotNull(foundEmployee);
        assertEquals("Meliodas", foundEmployee.getFirstName());
        assertEquals("Dragon de la Ira", foundEmployee.getLastName());
    }

    @Test
    public void testCreateEmployee() {
        Employee employee = getEmployee("12345679");
        when(repository.save(employee)).thenReturn(employee);

        Employee createEmployee = service.addEmployee(employee);

        assertNotNull(createEmployee);
        assertEquals(employee, createEmployee);
    }

    @Test
    public void testUpdateEmployee() {

        Employee employeeNew = getEmployee("123456789");

        when(repository.save(any(Employee.class))).thenReturn(employeeNew);

        Employee result = service.updateEmployee(employeeNew);

        assertNotNull(result);
        assertEquals(employeeNew, result);
    }

    @Test
    public void testDeleteEmployee() {
        Employee employeeNew = getEmployee("123456789");
        when(repository.findById(employeeNew.getId())).thenReturn(Optional.of(employeeNew));
        service.deleteEmployee(employeeNew);
        verify(repository, times(1)).delete(employeeNew);
    }

    @Test
    public void testFindEmployeeByDocument() {
        Employee employeeNew = getEmployee("123456789");
        when(repository.findEmployeeByDocumentNumberAndDocumentType(employeeNew.getDocumentNumber(), employeeNew.getDocumentType())).thenReturn(Optional.of(employeeNew));
        Employee result = service.getEmployeeByDocument(employeeNew.getDocumentNumber(), employeeNew.getDocumentType());
        assertNotNull(result);
        assertEquals(employeeNew, result);
    }

    @Test
    public void testFindEmployeeByName() {
        Employee employeeNew = getEmployee("123456789");
        when(repository.findEmployeeByFirstNameAndLastName(employeeNew.getFirstName(), employeeNew.getLastName())).thenReturn(Optional.of(employeeNew));
        Employee result = service.getEmployeeByFullName(employeeNew.getFirstName(), employeeNew.getLastName());
        assertNotNull(result);
        assertEquals(employeeNew, result);
    }

    private Employee getEmployee(String document) {
        return new Employee(
                0L,
                "Meliodas",
                "Dragon de la Ira",
                Date.from((LocalDate.of(1985, 5, 15)).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                "DNI",
                document,
                "",
                "+541112345678",
                "meliodas.dragon@example.com",
                LocalDateTime.now()
        );
    }

}
