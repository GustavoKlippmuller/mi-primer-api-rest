package com.std.ec.model.dao;

import com.std.ec.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Long> {
    Optional<Employee> findEmployeeByDocumentNumberAndDocumentType(String documentNumber, String documentType);
    Optional<Employee> findEmployeeByFirstNameAndLastName(String firstName, String lastName);
}
