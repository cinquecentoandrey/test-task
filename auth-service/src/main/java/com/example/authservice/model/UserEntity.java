package com.example.authservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component("UserEntity")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "users_user_id_gen")
    @GenericGenerator(
            name = "users_user_id_gen",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "users_user_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "user_id")
    private Long id;

    @NotEmpty(message = "Username field must not be empty.")
    @Column(name = "username")
    private String username;

    @NotEmpty(message = "Password field must not be empty.")
    @Column(name = "password")
    private String password;

    @Email
    @NotEmpty(message = "Email field must not be empty.")
    @Column(name = "email")
    private String email;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_approved")
    private Boolean isApproved;

    @Column(name = "role")
    private String role;

    @PrePersist
    public void onUpload() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.isApproved = false;
    }

    @PostUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
