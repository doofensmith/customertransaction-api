package com.wahyu.transaction.controller;

import com.wahyu.transaction.domain.request.PrintReportRequest;
import com.wahyu.transaction.domain.request.TransactionRequest;
import com.wahyu.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping(value = "/new")
    public ResponseEntity<Object> newTransaction(@RequestBody TransactionRequest request) {
        try {
            return transactionService.newTransaction(request);
        }catch (Exception e) {
            throw e;
        }
    }

    @PostMapping(value = "/print")
    public ResponseEntity<Object> printReport(@RequestBody PrintReportRequest request) {
        try {
            return transactionService.printReport(request);
        }catch (Exception e) {
            throw e;
        }
    }
}
