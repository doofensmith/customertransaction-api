package com.wahyu.transaction.controller;

import com.wahyu.transaction.domain.dto.AccountDto;
import com.wahyu.transaction.domain.dto.AccountWithPointDto;
import com.wahyu.transaction.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping(value = "")
    public ResponseEntity<Object> getAll() {
        try {
            return accountService.getAll();
        }catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/point")
    public ResponseEntity<Object> countPoint() {
        try {
            return accountService.countPoint();
        }catch (Exception e) {
            throw e;
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getById(@PathVariable Integer id) {
        try {
            return accountService.getById(id);
        }catch (Exception e) {
            throw e;
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> addNew(@RequestBody AccountWithPointDto request) {
        try {
            return accountService.addNew(request);
        }catch (Exception e) {
            throw e;
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> update(
            @PathVariable Integer id,
            @RequestBody AccountDto request) {
        try {
            return accountService.update(id, request);
        }catch (Exception e) {
            throw e;
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        try {
            return accountService.delete(id);
        }catch (Exception e) {
            throw e;
        }
    }

}
