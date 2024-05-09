package com.novin.invoicemanagementsystem.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleInput {
    @NotNull(message = "Roles most not be null")
    @Size(min = 1, max = 3)
    private Collection<String> roles;
}
