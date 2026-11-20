package com.delivery.carrinhodelivery.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record AdicionarItemDTO(
        @NotNull(message = "O ID do produto é obrigatorio")
        UUID idProduto,

        @NotNull(message = "A quantidade é obrigatoria")
        @Positive(message = "A quantidade deve ser maior com zero")
        Integer quantidade
) {}
