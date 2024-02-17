package com.desafiopicpay.services;

import com.desafiopicpay.domain.user.User;
import com.desafiopicpay.domain.user.UserType;
import com.desafiopicpay.dtos.UserDTO;
import com.desafiopicpay.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

//    @Autowired
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {

        if (sender.getUserType() == UserType.MERCHANT) {
            throw new Exception("Usuario do tipo lojista não está autorizado a realizar transações.");
        }

        if (sender.getBalance().compareTo(amount) < 0) { // Método da interface Comparable
            throw new Exception("Usuario não tem saldo insuficiente para realizar a transação");
        }

    }

    public User findUserById(Long id) throws Exception {
        return this.userRepository.findById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public void saveUser(User user) {
        this.userRepository.save(user);
    }

    public User createUser(UserDTO user) {
        User newUser = new User(user);

        this.saveUser(newUser);

        return newUser;
    }

    public List<User> listUsers() {
        return this.userRepository.findAll();
    }
}
