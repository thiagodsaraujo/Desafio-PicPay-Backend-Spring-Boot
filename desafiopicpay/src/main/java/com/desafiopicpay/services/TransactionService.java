package com.desafiopicpay.services;

import com.desafiopicpay.domain.transaction.Transaction;
import com.desafiopicpay.domain.user.User;
import com.desafiopicpay.dtos.TransactionDTO;
import com.desafiopicpay.repository.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class TransactionService {

    private final UserService userService;

    private final RestTemplate restTemplate;

    private final NotificationService notificationService;

    private final TransactionRepository transactionRepository;

    public TransactionService(UserService userService, RestTemplate restTemplate, NotificationService notificationService, TransactionRepository transactionRepository) {
        this.userService = userService;
        this.restTemplate = restTemplate;
        this.notificationService = notificationService;
        this.transactionRepository = transactionRepository;
    }

    public Transaction createTransaction(TransactionDTO transactionDTO) throws Exception {

        User sender = this.userService.findUserById(transactionDTO.senderId());

        User receiver = this.userService.findUserById(transactionDTO.receiverId());

        this.userService.validateTransaction(sender, transactionDTO.value());

        var isAuthorized = this.authorizeTransaction(sender, transactionDTO.value());

        if (!isAuthorized) {
            throw new Exception("Transação não autorizada");
        }

        Transaction newTransaction = new Transaction();

        newTransaction.setAmount(transactionDTO.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transactionDTO.value())); // o novo saldo do sender é o saldo atual menos o valor da transação
        receiver.setBalance(receiver.getBalance().add(transactionDTO.value())); // o novo saldo do receiver é o saldo atual mais o valor da transação

        // agora persistir as alterações no banco de dados

        this.transactionRepository.save(newTransaction);

        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

        this.notificationService.sendNotification(sender, "Transação realizada com sucesso");
        this.notificationService.sendNotification(receiver, "Você recebeu uma transação no valor de R$" + transactionDTO.value());


        return newTransaction;


    }

    public boolean authorizeTransaction(User sender, BigDecimal value) {

        // o ideal não é colocar hardcoded e sim como variável de ambiente
        ResponseEntity<Map> authorizationResponse =
                restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", Map.class);

        if (authorizationResponse.getStatusCode() == HttpStatus.OK) {
            String message = (String) authorizationResponse.getBody().get("message");

        return "Autorizado".equalsIgnoreCase(message); // "Autorizado" é o valor esperado no JSON de resposta

        } else return false;
    }

    public List<Transaction> listTransactions() {
        return this.transactionRepository.findAll();
    }
}
