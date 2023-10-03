package com.cinqeucento.engineservice.util;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
@NoArgsConstructor
public class ErrorMessageBuilder {

    public String message(BindingResult bindingResult) {
        StringBuilder errorMessage = new StringBuilder();

        bindingResult.getFieldErrors().forEach(e -> errorMessage.append(e.getField())
                .append(" - ")
                .append(e.getDefaultMessage())
                .append("; "));

        return errorMessage.toString();
    }
}
