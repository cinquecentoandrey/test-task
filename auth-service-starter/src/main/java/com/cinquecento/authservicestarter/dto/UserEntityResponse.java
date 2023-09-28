package com.cinquecento.authservicestarter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntityResponse {

    private Integer errorCode;
    private String message;
    private String timestamp;

}
