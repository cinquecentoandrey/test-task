package com.cinqucento.engineservicestarter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskEntityResponse {

    private Integer errorCode;
    private String message;
    private String date;
}
