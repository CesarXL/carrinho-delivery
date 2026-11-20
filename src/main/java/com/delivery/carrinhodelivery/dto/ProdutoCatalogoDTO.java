package com.delivery.carrinhodelivery.dto;

import java.util.UUID;

public record ProdutoCatalogoDTO(
        UUID idProduto,
        String nome,
        Long precoCentavos
) {}
