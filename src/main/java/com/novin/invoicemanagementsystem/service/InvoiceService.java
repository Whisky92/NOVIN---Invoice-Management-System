package com.novin.invoicemanagementsystem.service;

import com.novin.invoicemanagementsystem.entity.Invoice;

import java.util.List;

public interface InvoiceService {
    List<Invoice> getAllInvoices();

    Invoice getInvoiceById(Long id);

    Invoice createInvoice(Invoice invoiceData);
}
