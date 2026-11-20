package com.delivery.carrinhodelivery.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Carrinho {

    private UUID idCarrinho;

    private UUID idUsuario;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;

    private List<ItemCarrinho> itens = new ArrayList<>();

    public Long calcularSubtotal(){
        return itens.stream()
                .mapToLong(ItemCarrinho::calcularSubTotal).sum();
    }
}
