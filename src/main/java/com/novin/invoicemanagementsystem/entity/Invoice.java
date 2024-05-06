package com.novin.invoicemanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;

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
    @NonNull
    private String customerName;
    @NonNull
    private Date creationDate;
    @NonNull
    private Date deadline;
    @NonNull
    private String itemName;
    @NonNull
    private String comment;
    @NonNull
    private Integer price;
}
