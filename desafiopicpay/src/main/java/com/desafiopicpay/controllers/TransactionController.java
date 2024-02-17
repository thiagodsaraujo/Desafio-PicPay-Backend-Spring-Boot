package com.desafiopicpay.controllers;

import com.desafiopicpay.domain.transaction.Transaction;
import com.desafiopicpay.domain.user.User;
import com.desafiopicpay.dtos.TransactionDTO;
import com.desafiopicpay.dtos.UserDTO;
import com.desafiopicpay.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transactionDTO) throws Exception {
        Transaction newTransction = this.transactionService.createTransaction(transactionDTO);

        return new ResponseEntity<>(newTransction, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> listUsers() {

        List<Transaction> transactions = transactionService.listTransactions();

        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
