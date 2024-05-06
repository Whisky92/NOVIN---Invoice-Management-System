package com.novin.invoicemanagementsystem.repository;

import com.novin.invoicemanagementsystem.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
