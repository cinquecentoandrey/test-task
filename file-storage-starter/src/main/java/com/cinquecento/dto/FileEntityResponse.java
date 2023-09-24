package com.cinquecento.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileEntityResponse {

    private Integer errorCode;
    private String message;
    private String date;

}
