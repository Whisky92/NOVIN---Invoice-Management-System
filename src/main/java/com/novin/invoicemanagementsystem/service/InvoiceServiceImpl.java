package com.novin.invoicemanagementsystem.service;

import com.novin.invoicemanagementsystem.entity.Invoice;
import com.novin.invoicemanagementsystem.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There is no invoice with such id"));
    }

    @Override
    public Invoice createInvoice(Invoice invoiceData) {
        Invoice newInvoice = Invoice.builder()
                .customerName(invoiceData.getCustomerName())
                .creationDate(invoiceData.getCreationDate())
                .deadline(invoiceData.getDeadline())
                .itemName(invoiceData.getItemName())
                .comment(invoiceData.getComment())
                .price(invoiceData.getPrice())
                .build();

        return invoiceRepository.save(newInvoice);
    }
}
