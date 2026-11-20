package com.delivery.carrinhodelivery.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AtualizarQuantidadeDTO(

        @NotNull(message = "A quantidade é obrigatoria")
        @Positive(message = "A quantidade deve ser maior que zero")
        Integer quantidade
) {}
