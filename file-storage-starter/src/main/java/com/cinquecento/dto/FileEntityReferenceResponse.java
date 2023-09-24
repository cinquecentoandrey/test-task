package com.cinquecento.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileEntityReferenceResponse {

    private String name;
    private String URI;
    private String type;
}
