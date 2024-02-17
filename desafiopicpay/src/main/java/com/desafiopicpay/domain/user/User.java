package com.desafiopicpay.domain.user;

import com.desafiopicpay.dtos.UserDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "users") // User é uma palavra reservada do SQL, por isso renomeamos a tabela para users
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Não é a forma mais segura
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = "O campo nome é obrigatório")
    private String firstName;

    @NotNull(message = "O campo sobrenome é obrigatório")
    private String lastName;

    @Column(unique = true)
    @NotNull(message = "O campo documento é obrigatório")
    private String document;

    @Column(unique = true)
    private String email;

    private String password;

    private BigDecimal balance;

    @Enumerated(EnumType.STRING) // Esse campo representa um dos valores do enum UserType
    private UserType userType;

    public User(UserDTO user) {

        this.firstName = user.firstName();
        this.lastName = user.lastName();
        this.email = user.email();
        this.document = user.document();
        this.password = user.password();
        this.balance = user.balance();
        this.userType = user.userType();
    }
}
