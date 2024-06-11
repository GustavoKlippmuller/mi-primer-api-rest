package com.std.ec.service;

import com.std.ec.model.dao.EmployeeDao;
import com.std.ec.model.entity.Employee;
import com.std.ec.service.impl.EmployeeImpl;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
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
        Employee employee1 = new Employee(
                0L,
                "Meliodas",
                "Dragon de la Ira",
                Date.from((LocalDate.of(1985, 5, 15)).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                "DNI",
                "12345678",
                "",
                "+541112345678",
                "meliodas.dragon@example.com"
        );
        Employee employee2 = new Employee(
                0L,
                "Elizabeth",
                "Liones",
                Date.from((LocalDate.of(1990, 8, 20)).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                "DNI",
                "23456789",
                "+541123456789",
                "",
                "elizabeth.liones@example.com"
        );

        when(repository.findAll()).thenReturn(Arrays.asList(employee1, employee2));

        List<Employee> employees = service.getEmployees();

        assertEquals(2, employees.size());
        Employee employee = employees.get(0);
        assertEquals("Meliodas", employee.getFirstName());
        assertEquals("Dragon de la Ira", employee.getLastName());
    }

    @Test
    public void testGetEmployeeById() {
        Employee employee = new Employee(
                0L,
                "Meliodas",
                "Dragon de la Ira",
                Date.from((LocalDate.of(1985, 5, 15)).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                "DNI",
                "12345678",
                "",
                "+541112345678",
                "meliodas.dragon@example.com"
        );
        when(repository.findById(1L)).thenReturn(Optional.of(employee));

        Employee foundEmployee = service.findById(1L);

        assertNotNull(foundEmployee);
        assertEquals("Meliodas", foundEmployee.getFirstName());
        assertEquals("Dragon de la Ira", foundEmployee.getLastName());
    }

    @Test
    public void testCreateEmployee() {
        Employee employee = new Employee(
                0L,
                "Meliodas",
                "Dragon de la Ira",
                Date.from((LocalDate.of(1985, 5, 15)).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                "DNI",
                "12345678",
                "",
                "+541112345678",
                "meliodas.dragon@example.com"
        );
        when(repository.save(employee)).thenReturn(employee);

        Employee createEmployee = service.addEmployee(employee);

        assertNotNull(createEmployee);
        assertEquals(employee, createEmployee);
    }

}
