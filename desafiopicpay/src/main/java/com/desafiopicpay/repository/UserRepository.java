package com.desafiopicpay.repository;

import com.desafiopicpay.domain.user.User;
import com.desafiopicpay.domain.user.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // o Jpa é inteligente o suficiente para entender que queremos buscar por email
    Optional<User> findByEmail(String email); // Não é certo que vai sempre retornar um usuario, por isso usamos Optional

    Optional<User> findByDocument(String document);

    List<User> findByUserType(UserType userType);

    Optional<User> findById(Long id);

}
