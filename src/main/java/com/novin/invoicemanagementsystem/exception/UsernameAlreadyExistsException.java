package com.novin.invoicemanagementsystem.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsernameAlreadyExistsException extends Exception {
    private String message;
}
