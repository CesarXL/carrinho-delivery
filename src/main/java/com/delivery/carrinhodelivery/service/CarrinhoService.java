package com.delivery.carrinhodelivery.service;

import com.delivery.carrinhodelivery.dto.AdicionarItemDTO;
import com.delivery.carrinhodelivery.dto.AtualizarQuantidadeDTO;
import com.delivery.carrinhodelivery.entity.Carrinho;

import java.util.UUID;

public interface CarrinhoService {

    Carrinho buscarCarrinho(UUID idUsuario);
    Carrinho criarCarrinho(UUID idUsuario);
    Carrinho adicionarItem(UUID idUsuario, AdicionarItemDTO dto);
    Carrinho atualizarQuantidade(UUID idUsuario, UUID idItem, AtualizarQuantidadeDTO dto);
    Carrinho removerItem(UUID idUsuario, UUID idItem);
    Carrinho esvaziarCarrinho(UUID idUsuario);


}
