package com.std.ec.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
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

    @NotBlank(message = "Not Empty First Name")
    @Column(nullable = false, length = 50)
    private String firstName;
    @Column(nullable = false, length = 50)
    private String lastName;
    @Column(nullable = false)
    private Date birthdate;
    @Column(nullable = false, length = 3)
    private String documentType;
    @Column(nullable = false, length = 8)
    private String documentNumber;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column(length = 14)
    private String phoneNumber;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column(length = 14)
    private String cellPhoneNumber;
    @Email
    @Column(nullable = false, length = 75)
    private String emailAddress;
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
