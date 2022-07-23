package com.wahyu.transaction.service;

import com.wahyu.transaction.constant.AppConstant;
import com.wahyu.transaction.constant.TransactionConstant;
import com.wahyu.transaction.domain.dao.AccountDao;
import com.wahyu.transaction.domain.dao.TransactionDao;
import com.wahyu.transaction.domain.dto.AccountDto;
import com.wahyu.transaction.domain.dto.AccountWithPointDto;
import com.wahyu.transaction.repository.AccountRepository;
import com.wahyu.transaction.repository.TransactionRepository;
import com.wahyu.transaction.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public ResponseEntity<Object> getAll() {
        List<AccountDao> accountDaoList = accountRepository.findAll();
        List<AccountDto> accountDtoList = new ArrayList<>();
        for (AccountDao accountDao : accountDaoList) {
            accountDtoList.add(AccountDto.builder()
                    .accountId(accountDao.getAccountId())
                    .name(accountDao.getName())
                    .build());
        }
        return ResponseUtil.build(HttpStatus.OK, AppConstant.SUCCESS.message, accountDtoList);
    }

    public ResponseEntity<Object> countPoint() {
        List<AccountDao> accountDaoList = accountRepository.findAll();
        List<AccountWithPointDto> accountDtoList = new ArrayList<>();
        for (AccountDao accountDao : accountDaoList) {
            List<TransactionDao> transactions = transactionRepository.findByAccount_AccountId(accountDao.getAccountId());
            Integer totalPoint = 0;
            for (TransactionDao transactionDao : transactions) {
                if (transactionDao.getDescription().equals(TransactionConstant.PULSA.transaction)) {
                    if (transactionDao.getAmount() <= Double.valueOf(10000)) {
                        totalPoint += 0;
                    } else if (transactionDao.getAmount() > Double.valueOf(10000) && transactionDao.getAmount() <= Double.valueOf(30000)) {
                        totalPoint += (Integer.valueOf((int) (transactionDao.getAmount()/1000) * 1));
                    } else if (transactionDao.getAmount() > 30000) {
                        totalPoint += (Integer.valueOf((int) (transactionDao.getAmount()/1000) * 2));
                    }
                } else if (transactionDao.getDescription().equals(TransactionConstant.LISTRIK.transaction)) {
                    if (transactionDao.getAmount() <= Double.valueOf(50000)) {
                        totalPoint += 0;
                    } else if (transactionDao.getAmount() > Double.valueOf(50000) && transactionDao.getAmount() <= Double.valueOf(100000)) {
                        totalPoint += (Integer.valueOf((int) (transactionDao.getAmount()/2000) * 1));
                    } else if (transactionDao.getAmount() > 100000) {
                        totalPoint += (Integer.valueOf((int) (transactionDao.getAmount()/2000) * 2));
                    }
                }
            }
            accountDtoList.add(AccountWithPointDto.builder()
                    .accountId(accountDao.getAccountId())
                    .name(accountDao.getName())
                    .totalPoint(totalPoint)
                    .build());
        }
        return ResponseUtil.build(HttpStatus.OK, AppConstant.SUCCESS.message, accountDtoList);
    }

    public ResponseEntity<Object> getById(Integer id) {
        Optional<AccountDao> accountDao = accountRepository.findById(id);
        if (accountDao.isPresent()) {
            AccountDto accountDto = AccountDto.builder()
                    .accountId(accountDao.get().getAccountId())
                    .name(accountDao.get().getName())
                    .build();
            return ResponseUtil.build(HttpStatus.OK, AppConstant.SUCCESS.message, accountDto);
        }else {
            return ResponseUtil.build(HttpStatus.BAD_REQUEST, AppConstant.NOT_FOUND.message, null);
        }
    }

    public ResponseEntity<Object> addNew(AccountDto request) {
        AccountDao accountDao = AccountDao.builder()
                .name(request.getName())
                .build();
        accountDao = accountRepository.save(accountDao);

        AccountDto accountDto = AccountDto.builder()
                .accountId(accountDao.getAccountId())
                .name(accountDao.getName())
                .build();

        return ResponseUtil.build(HttpStatus.OK, AppConstant.SUCCESS.message, accountDto);
    }

    public ResponseEntity<Object> update(Integer id, AccountDto request) {
        Optional<AccountDao> accountDaoOld = accountRepository.findById(id);
        if (accountDaoOld.isPresent()) {
            AccountDao accountDao = accountDaoOld.get();
            accountDao.setName(request.getName());
            accountDao = accountRepository.save(accountDao);

            AccountDto accountDto = AccountDto.builder()
                    .accountId(accountDao.getAccountId())
                    .name(accountDao.getName())
                    .build();

            return ResponseUtil.build(HttpStatus.OK, AppConstant.SUCCESS.message, accountDto);
        }else {
            return ResponseUtil.build(HttpStatus.BAD_REQUEST, AppConstant.NOT_FOUND.message, null);
        }
    }

    public ResponseEntity<Object> delete(Integer id) {
        Optional<AccountDao> accountDaoOld = accountRepository.findById(id);
        if (accountDaoOld.isPresent()) {
            accountRepository.deleteById(id);
            return ResponseUtil.build(HttpStatus.OK, AppConstant.SUCCESS.message, null);
        }else {
            return ResponseUtil.build(HttpStatus.BAD_REQUEST, AppConstant.NOT_FOUND.message, null);
        }
    }

}
