package com.desafiopicpay.dtos;

import com.desafiopicpay.domain.user.UserType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UserDTO(

        @NotNull(message = "O campo nome é obrigatório")
        @NotBlank(message = "O campo nome não pode ser vazio")
        String firstName,

        @NotNull(message = "O campo sobrenome é obrigatório")
        @NotBlank(message = "O campo sobrenome não pode ser vazio")
        String lastName,

        String email,

        String password,

        BigDecimal balance,

        UserType userType,

        String document
) {
}
