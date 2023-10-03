package com.cinqucento.engineservicestarter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectEntityResponse {

    private Integer errorCode;
    private String message;
    private String date;
}
