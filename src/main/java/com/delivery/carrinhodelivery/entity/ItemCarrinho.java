package com.delivery.carrinhodelivery.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemCarrinho {
    private UUID idItem;

    private UUID idProduto;

    private String nomeProduto;

    private Long precoUnitarioCentavos;

    private Integer quantidade;

    public Long calcularSubTotal(){
        return Math.multiplyExact(
                precoUnitarioCentavos,
                quantidade.longValue());
    }

}
