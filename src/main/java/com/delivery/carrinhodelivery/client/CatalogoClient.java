package com.delivery.carrinhodelivery.client;

import com.delivery.carrinhodelivery.dto.ProdutoCatalogoDTO;

import java.util.UUID;

public interface CatalogoClient {

    ProdutoCatalogoDTO buscarProduto(UUID idProduto);

}
