package com.std.ec.model.configuration;

import com.std.ec.model.dao.EmployeeDao;
import com.std.ec.model.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Configuration
public class DataInitializer {

    @Autowired
    private EmployeeDao employeeDao;

    @Bean
    public CommandLineRunner loadData() {
        return args -> {
            employeeDao.save(new Employee(
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
            ));
            employeeDao.save(new Employee(
                    0L,
                    "Elizabeth",
                    "Liones",
                    Date.from((LocalDate.of(1990, 8, 20)).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    "DNI",
                    "23456789",
                    "+541123456789",
                    "",
                    "elizabeth.liones@example.com",
                    LocalDateTime.now()
            ));
            employeeDao.save(new Employee(
                    0L,
                    "Diane",
                    "Serpiente de la Envidia",
                    Date.from((LocalDate.of(1978, 2, 10)).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    "DNI",
                    "34567890",
                    "+5491134567890",
                    "",
                    "diane.serpent@example.com",
                    LocalDateTime.now()
            ));
            employeeDao.save(new Employee(
                    0L,
                    "Ban",
                    "Zorro de la Avaricia",
                    Date.from((LocalDate.of(1984, 9, 13)).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    "DNI",
                    "45678901",
                    "+5491145678901",
                    "",
                    "ban.fox@example.com",
                    LocalDateTime.now()
            ));
            employeeDao.save(new Employee(
                    0L,
                    "King",
                    "Oso de la Pereza",
                    Date.from((LocalDate.of(1995, 12, 5)).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    "DNI",
                    "56789012",
                    "",
                    "+5411156789012",
                    "king.grizzly@example.com",
                    LocalDateTime.now()
            ));
            employeeDao.save(new Employee(
                    0L,
                    "Gowther",
                    "Cabra de la Lujuria",
                    Date.from((LocalDate.of(1986, 4, 18)).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    "DNI",
                    "67890123",
                    "+541167890123",
                    "",
                    "gowther.goat@example.com",
                    LocalDateTime.now()
            ));
            employeeDao.save(new Employee(
                    0L,
                    "Merlin",
                    "Jabali de la Gula",
                    Date.from((LocalDate.of(1991, 3, 12)).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    "DNI",
                    "78901234",
                    "+5491178901234",
                    "",
                    "merlin.boar@example.com",
                    LocalDateTime.now()
            ));
            employeeDao.save(new Employee(
                    0L,
                    "Escanor",
                    "Leon del Orgullo",
                    Date.from((LocalDate.of(1984, 11, 2)).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    "DNI",
                    "89012345",
                    "+5491189012345",
                    "",
                    "escanor.lion@example.com",
                    LocalDateTime.now()
            ));
        };
    }
}
