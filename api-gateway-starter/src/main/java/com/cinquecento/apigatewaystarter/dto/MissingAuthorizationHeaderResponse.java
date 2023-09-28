package com.cinquecento.apigatewaystarter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MissingAuthorizationHeaderResponse {

    private Integer errorCode;
    private String message;
    private String date;
}
