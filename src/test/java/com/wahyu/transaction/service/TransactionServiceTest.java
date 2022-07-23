package com.wahyu.transaction.service;

import com.wahyu.transaction.constant.TransactionConstant;
import com.wahyu.transaction.domain.dao.AccountDao;
import com.wahyu.transaction.domain.dao.TransactionDao;
import com.wahyu.transaction.domain.request.PrintReportRequest;
import com.wahyu.transaction.domain.request.TransactionRequest;
import com.wahyu.transaction.repository.AccountRepository;
import com.wahyu.transaction.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TransactionService.class)
class TransactionServiceTest {

    @MockBean
    private TransactionRepository transactionRepository;

    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private TransactionService transactionService;

    @Test
    void newTransaction_Success_Test() {
        //mocking
        AccountDao accountDao = AccountDao.builder()
                .accountId(1)
                .build();

        TransactionDao transactionDao = TransactionDao.builder()
                .account(AccountDao.builder()
                        .accountId(1)
                        .build())
                .description(TransactionConstant.LISTRIK.transaction)
                .amount(Double.valueOf(110000))
                .build();

        TransactionRequest request = TransactionRequest.builder()
                .accountId(1)
                .description(TransactionConstant.PULSA.transaction)
                .amount(Double.valueOf(1))
                .build();

        when(accountRepository.findById(anyInt())).thenReturn(Optional.of(accountDao));
        when(transactionRepository.save(any())).thenReturn(transactionDao);

        //test
        ResponseEntity<Object> response = transactionService.newTransaction(request);

        //assert
        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
    }

    @Test
    void newTransaction_Failed_Test() {
        //mocking
        TransactionDao transactionDao = TransactionDao.builder()
                .account(AccountDao.builder()
                        .accountId(1)
                        .build())
                .description(TransactionConstant.LISTRIK.transaction)
                .amount(Double.valueOf(110000))
                .build();

        TransactionRequest request = TransactionRequest.builder()
                .accountId(1)
                .description(TransactionConstant.PULSA.transaction)
                .amount(Double.valueOf(1))
                .build();

        when(accountRepository.findById(anyInt())).thenReturn(Optional.empty());
        when(transactionRepository.save(any())).thenReturn(transactionDao);

        //test
        ResponseEntity<Object> response = transactionService.newTransaction(request);

        //assert
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCodeValue());
    }

    @Test
    void printReport_Success_Debit_Test() {
        //mocking
        AccountDao accountDao = AccountDao.builder()
                .accountId(1)
                .build();
        TransactionDao transactionDao = TransactionDao.builder()
                .account(AccountDao.builder()
                        .accountId(1)
                        .build())
                .description(TransactionConstant.LISTRIK.transaction)
                .debitCreditStatus("D")
                .transactionDate(LocalDateTime.now())
                .amount(Double.valueOf(110000))
                .build();

        PrintReportRequest request = PrintReportRequest.builder()
                .accountId(1)
                .startDate("23/07/2022")
                .endDate("24/07/2022")
                .build();

        when(accountRepository.findById(anyInt())).thenReturn(Optional.of(accountDao));
        when(transactionRepository.findByAccount_AccountIdAndTransactionDateBetweenOrderByTransactionDateAsc(anyInt(), any(), any())).thenReturn(List.of(transactionDao));

        //test
        ResponseEntity<Object> response = transactionService.printReport(request);

        //assert
        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
    }

    @Test
    void printReport_Success_Credit_Test() {
        //mocking
        AccountDao accountDao = AccountDao.builder()
                .accountId(1)
                .build();
        TransactionDao transactionDao = TransactionDao.builder()
                .account(AccountDao.builder()
                        .accountId(1)
                        .build())
                .description(TransactionConstant.LISTRIK.transaction)
                .debitCreditStatus("C")
                .transactionDate(LocalDateTime.now())
                .amount(Double.valueOf(110000))
                .build();

        PrintReportRequest request = PrintReportRequest.builder()
                .accountId(1)
                .startDate("23/07/2022")
                .endDate("24/07/2022")
                .build();

        when(accountRepository.findById(anyInt())).thenReturn(Optional.of(accountDao));
        when(transactionRepository.findByAccount_AccountIdAndTransactionDateBetweenOrderByTransactionDateAsc(anyInt(), any(), any())).thenReturn(List.of(transactionDao));

        //test
        ResponseEntity<Object> response = transactionService.printReport(request);

        //assert
        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
    }

    @Test
    void printReport_Failed_Test() {
        //mocking
        AccountDao accountDao = AccountDao.builder()
                .accountId(1)
                .build();
        TransactionDao transactionDao = TransactionDao.builder()
                .account(AccountDao.builder()
                        .accountId(1)
                        .build())
                .description(TransactionConstant.LISTRIK.transaction)
                .debitCreditStatus("D")
                .transactionDate(LocalDateTime.now())
                .amount(Double.valueOf(110000))
                .build();

        PrintReportRequest request = PrintReportRequest.builder()
                .accountId(1)
                .startDate("23/07/2022")
                .endDate("24/07/2022")
                .build();

        when(accountRepository.findById(anyInt())).thenReturn(Optional.empty());
        when(transactionRepository.findByAccount_AccountIdAndTransactionDateBetweenOrderByTransactionDateAsc(anyInt(), any(), any())).thenReturn(List.of(transactionDao));

        //test
        ResponseEntity<Object> response = transactionService.printReport(request);

        //assert
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCodeValue());
    }

}