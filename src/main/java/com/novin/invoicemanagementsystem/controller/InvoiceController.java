package com.novin.invoicemanagementsystem.controller;

import com.novin.invoicemanagementsystem.entity.Invoice;
import com.novin.invoicemanagementsystem.service.InvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/invoices")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;

    @GetMapping("/getAllInvoices")
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        return ResponseEntity.ok().body(invoiceService.getAllInvoices());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id) {
        return ResponseEntity.ok().body(invoiceService.getInvoiceById(id));
    }

    @PostMapping("/createInvoice")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'ACCOUNTANT')")
    public ResponseEntity<Invoice> createInvoice(@Valid @RequestBody Invoice invoice) {
        return ResponseEntity.ok().body(invoiceService.createInvoice(invoice));
    }
}
