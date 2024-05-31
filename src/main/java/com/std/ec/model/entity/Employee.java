package com.std.ec.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private Date birthdate;
    private String documentType;
    private String documentNumber;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String phoneNumber;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String cellPhoneNumber;
    private String emailAddress;
}
