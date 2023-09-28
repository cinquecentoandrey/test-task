package com.cinquecento.authservicestarter.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegistrationUserEntityDTO {


    @NotEmpty(message = "Username field must not be empty.")
    private String username;

    @NotEmpty(message = "Password field must not be empty.")
    private String password;

    @Email
    @NotEmpty(message = "Email field must not be empty.")
    private String email;

}
