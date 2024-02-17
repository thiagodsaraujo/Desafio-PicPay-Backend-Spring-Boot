package com.desafiopicpay.dtos;

public record ExceptionDTO(String message, String status, java.util.Map<String, String> errors) {
}
