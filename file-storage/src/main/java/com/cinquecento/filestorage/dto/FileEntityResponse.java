package com.cinquecento.filestorage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileEntityResponse {

    private Integer errorCode;
    private String message;
    private String date;

}
