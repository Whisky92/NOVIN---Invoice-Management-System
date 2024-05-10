package com.novin.invoicemanagementsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Customer name is mandatory")
    private String customerName;

    @NotNull(message = "Creation date is mandatory")
    private Date creationDate;

    @NotNull(message = "Deadline is mandatory")
    private Date deadline;

    @NotBlank(message = "Item name is mandatory")
    private String itemName;

    @NotBlank(message = "Comment is mandatory")
    private String comment;

    @Min(1)
    @NotNull(message = "Price is mandatory")
    private Integer price;
}