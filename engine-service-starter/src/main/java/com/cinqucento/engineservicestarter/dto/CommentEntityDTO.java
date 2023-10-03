package com.cinqucento.engineservicestarter.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentEntityDTO {

    private Long id;

    @NotEmpty(message = "The comment of the content must not be empty.")
    @Size(max = 128, message = "The comment of the content must be less than 128 symbols.")
    private String commentContent;

}
