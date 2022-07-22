package com.wahyu.transaction.service;

import com.wahyu.transaction.constant.AppConstant;
import com.wahyu.transaction.domain.dao.AccountDao;
import com.wahyu.transaction.domain.dao.TransactionDao;
import com.wahyu.transaction.domain.dto.TransactionReportDto;
import com.wahyu.transaction.domain.request.PrintReportRequest;
import com.wahyu.transaction.domain.request.TransactionRequest;
import com.wahyu.transaction.repository.AccountRepository;
import com.wahyu.transaction.repository.TransactionRepository;
import com.wahyu.transaction.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    public ResponseEntity<Object> newTransaction(TransactionRequest request) {
        Optional<AccountDao> accountDao = accountRepository.findById(request.getAccountId());
        if (accountDao.isPresent()) {
            TransactionDao transactionDao = TransactionDao.builder()
                    .account(AccountDao.builder()
                            .accountId(request.getAccountId())
                            .build())
                    .transactionDate(LocalDateTime.now(ZoneId.of("GMT+7")))
                    .description(request.getDescription())
                    .debitCreditStatus(request.getDebitCreditStatus())
                    .amount(request.getAmount())
                    .build();
            transactionDao = transactionRepository.save(transactionDao);

            TransactionRequest result = TransactionRequest.builder()
                    .accountId(transactionDao.getAccount().getAccountId())
                    .description(transactionDao.getDescription())
                    .transactionDate(transactionDao.getTransactionDate())
                    .debitCreditStatus(transactionDao.getDebitCreditStatus())
                    .amount(transactionDao.getAmount())
                    .build();

            return ResponseUtil.build(HttpStatus.OK, AppConstant.SUCCESS.message, result);
        }else {
            return ResponseUtil.build(HttpStatus.BAD_REQUEST, AppConstant.NOT_FOUND.message, null);
        }
    }

    public ResponseEntity<Object> printReport(PrintReportRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime startDate = LocalDateTime.parse(request.getStartDate().toString()+" 00:00", formatter);
        LocalDateTime endDate = LocalDateTime.parse(request.getEndDate().toString()+" 00:00", formatter);
        Optional<AccountDao> accountDao = accountRepository.findById(request.getAccountId());
        if (accountDao.isPresent()) {
            List<TransactionDao> transactionDaoList = transactionRepository.findByAccount_AccountIdAndTransactionDateBetweenOrderByTransactionDateAsc(request.getAccountId(), startDate, endDate);
            List<TransactionReportDto> transactionReportDtoList = new ArrayList<>();
            for (TransactionDao transactionDao : transactionDaoList) {
                if (transactionDao.getDebitCreditStatus().equals("C")) {
                    transactionReportDtoList.add(TransactionReportDto.builder()
                            .transactionDate(transactionDao.getTransactionDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                            .description(transactionDao.getDescription())
                            .credit(transactionDao.getAmount())
                            .debit(Double.valueOf(0))
                            .amount(transactionDao.getAmount())
                            .build());
                }else {
                    transactionReportDtoList.add(TransactionReportDto.builder()
                            .transactionDate(transactionDao.getTransactionDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                            .description(transactionDao.getDescription())
                            .credit(Double.valueOf(0))
                            .debit(transactionDao.getAmount())
                            .amount(transactionDao.getAmount())
                            .build());
                }
            }
            return ResponseUtil.build(HttpStatus.OK, AppConstant.SUCCESS.message, transactionReportDtoList);
        }else {
            return ResponseUtil.build(HttpStatus.BAD_REQUEST, AppConstant.NOT_FOUND.message, null);
        }
    }

}
