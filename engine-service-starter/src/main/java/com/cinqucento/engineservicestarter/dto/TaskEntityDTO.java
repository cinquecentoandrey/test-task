package com.cinqucento.engineservicestarter.dto;

import com.cinqucento.engineservicestarter.model.CurrentTaskStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskEntityDTO {

    private Long id;

    @NotEmpty(message = "The task name must not be empty.")
    @Size(max = 32, message = "The task name must be less than 128 symbols.")
    private String taskName;

    @NotEmpty(message = "The task description must not be empty.")
    @Size(max = 512, message = "The task description must be less than 512 symbols.")
    private String taskDescription;

    //
    private CurrentTaskStatus taskStatus;

    private LocalDateTime createdAt;

    private LocalDateTime lastStatusUpdate;
}
