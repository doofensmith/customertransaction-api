package com.wahyu.transaction.service;

import com.wahyu.transaction.constant.TransactionConstant;
import com.wahyu.transaction.domain.dao.AccountDao;
import com.wahyu.transaction.domain.dao.TransactionDao;
import com.wahyu.transaction.domain.dto.AccountDto;
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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AccountService.class)
class AccountServiceTest {

    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    @Test
    void getAll_Success_Test() {
        //mocking
        AccountDao accountDao = AccountDao.builder()
                .accountId(1)
                .build();

        when(accountRepository.findAll()).thenReturn(List.of(accountDao));

        //test
        ResponseEntity<Object> response = accountService.getAll();

        //assert
        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
    }

    @Test
    void count_Pulsa_Under10_Test() {
        //mocking
        AccountDao accountDao = AccountDao.builder()
                .accountId(1)
                .build();
        TransactionDao transactionDao = TransactionDao.builder()
                .account(AccountDao.builder()
                        .accountId(1)
                        .build())
                .description(TransactionConstant.PULSA.transaction)
                .amount(Double.valueOf(10000))
                .build();

        when(accountRepository.findAll()).thenReturn(List.of(accountDao));
        when(transactionRepository.findByAccount_AccountId(anyInt())).thenReturn(List.of(transactionDao));

        //test
        ResponseEntity<Object> response = accountService.countPoint();

        //assert
        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
    }

    @Test
    void count_Pulsa_Between10_30_Test() {
        //mocking
        AccountDao accountDao = AccountDao.builder()
                .accountId(1)
                .build();
        TransactionDao transactionDao = TransactionDao.builder()
                .account(AccountDao.builder()
                        .accountId(1)
                        .build())
                .description(TransactionConstant.PULSA.transaction)
                .amount(Double.valueOf(12000))
                .build();

        when(accountRepository.findAll()).thenReturn(List.of(accountDao));
        when(transactionRepository.findByAccount_AccountId(anyInt())).thenReturn(List.of(transactionDao));

        //test
        ResponseEntity<Object> response = accountService.countPoint();

        //assert
        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
    }

    @Test
    void count_Pulsa_Above30_Test() {
        //mocking
        AccountDao accountDao = AccountDao.builder()
                .accountId(1)
                .build();
        TransactionDao transactionDao = TransactionDao.builder()
                .account(AccountDao.builder()
                        .accountId(1)
                        .build())
                .description(TransactionConstant.PULSA.transaction)
                .amount(Double.valueOf(31000))
                .build();

        when(accountRepository.findAll()).thenReturn(List.of(accountDao));
        when(transactionRepository.findByAccount_AccountId(anyInt())).thenReturn(List.of(transactionDao));

        //test
        ResponseEntity<Object> response = accountService.countPoint();

        //assert
        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
    }

    @Test
    void count_Listrik_Under50_Test() {
        //mocking
        AccountDao accountDao = AccountDao.builder()
                .accountId(1)
                .build();
        TransactionDao transactionDao = TransactionDao.builder()
                .account(AccountDao.builder()
                        .accountId(1)
                        .build())
                .description(TransactionConstant.LISTRIK.transaction)
                .amount(Double.valueOf(50000))
                .build();

        when(accountRepository.findAll()).thenReturn(List.of(accountDao));
        when(transactionRepository.findByAccount_AccountId(anyInt())).thenReturn(List.of(transactionDao));

        //test
        ResponseEntity<Object> response = accountService.countPoint();

        //assert
        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
    }

    @Test
    void count_Listrik_Between50_100_Test() {
        //mocking
        AccountDao accountDao = AccountDao.builder()
                .accountId(1)
                .build();
        TransactionDao transactionDao = TransactionDao.builder()
                .account(AccountDao.builder()
                        .accountId(1)
                        .build())
                .description(TransactionConstant.LISTRIK.transaction)
                .amount(Double.valueOf(70000))
                .build();

        when(accountRepository.findAll()).thenReturn(List.of(accountDao));
        when(transactionRepository.findByAccount_AccountId(anyInt())).thenReturn(List.of(transactionDao));

        //test
        ResponseEntity<Object> response = accountService.countPoint();

        //assert
        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
    }

    @Test
    void count_Listrik_Above100_Test() {
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

        when(accountRepository.findAll()).thenReturn(List.of(accountDao));
        when(transactionRepository.findByAccount_AccountId(anyInt())).thenReturn(List.of(transactionDao));

        //test
        ResponseEntity<Object> response = accountService.countPoint();

        //assert
        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
    }

    @Test
    void getById_Success_Test() {
        //mocking
        AccountDao accountDao = AccountDao.builder()
                .accountId(1)
                .build();

        when(accountRepository.findById(anyInt())).thenReturn(Optional.of(accountDao));

        //test
        ResponseEntity<Object> response = accountService.getById(anyInt());

        //assert
        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
    }

    @Test
    void getById_Failed_Test() {
        //mocking
        AccountDao accountDao = AccountDao.builder()
                .accountId(1)
                .build();

        when(accountRepository.findById(anyInt())).thenReturn(Optional.empty());

        //test
        ResponseEntity<Object> response = accountService.getById(anyInt());

        //assert
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCodeValue());
    }

    @Test
    void addNew_Success_Test() {
        //mocking
        AccountDao accountDao = AccountDao.builder()
                .accountId(1)
                .build();

        AccountDto request = AccountDto.builder()
                .name("tes")
                .build();

        when(accountRepository.save(any())).thenReturn(accountDao);

        //test
        ResponseEntity<Object> response = accountService.addNew(request);

        //assert
        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
    }

    @Test
    void update_Success_Test() {
        //mocking
        AccountDao accountDao = AccountDao.builder()
                .accountId(1)
                .build();

        AccountDto request = AccountDto.builder()
                .name("tes")
                .build();

        when(accountRepository.findById(anyInt())).thenReturn(Optional.of(accountDao));
        when(accountRepository.save(any())).thenReturn(accountDao);

        //test
        ResponseEntity<Object> response = accountService.update(1, request);

        //assert
        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
    }

    @Test
    void update_Failed_Test() {
        //mocking
        AccountDao accountDao = AccountDao.builder()
                .accountId(1)
                .build();

        AccountDto request = AccountDto.builder()
                .name("tes")
                .build();

        when(accountRepository.findById(anyInt())).thenReturn(Optional.empty());
        when(accountRepository.save(any())).thenReturn(accountDao);

        //test
        ResponseEntity<Object> response = accountService.update(1, request);

        //assert
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCodeValue());
    }

    @Test
    void delete_Success_Test() {
        //mocking
        AccountDao accountDao = AccountDao.builder()
                .accountId(1)
                .build();

        when(accountRepository.findById(anyInt())).thenReturn(Optional.of(accountDao));

        //test
        ResponseEntity<Object> response = accountService.delete(1);

        //assert
        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
    }

    @Test
    void delete_Failed_Test() {
        //mocking
        AccountDao accountDao = AccountDao.builder()
                .accountId(1)
                .build();

        when(accountRepository.findById(anyInt())).thenReturn(Optional.empty());

        //test
        ResponseEntity<Object> response = accountService.delete(1);

        //assert
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCodeValue());
    }

}