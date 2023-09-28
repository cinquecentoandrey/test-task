package com.cinquecento.authservicestarter.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserEntityDTO {

    private Long id;

    @NotEmpty(message = "Username field must not be empty.")
    private String username;

    @NotEmpty(message = "Password field must not be empty.")
    private String password;

    @Email
    @NotEmpty(message = "Email field must not be empty.")
    private String email;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Boolean isApproved;

    private String role;
}
