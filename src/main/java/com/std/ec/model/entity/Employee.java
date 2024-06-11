package com.std.ec.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"documentType", "documentNumber"})
})
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private Date birthdate;
    @Column(nullable = false)
    private String documentType;
    @Column(nullable = false)
    private String documentNumber;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String phoneNumber;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String cellPhoneNumber;
    @Column(nullable = false)
    @NotEmpty(message = "El correo electrónico no puede estar vacío")
    @Email(message = "El correo electrónico no es válido")
    private String emailAddress;
}
