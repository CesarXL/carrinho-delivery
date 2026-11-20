package com.delivery.carrinhodelivery.repository;

import com.delivery.carrinhodelivery.entity.Carrinho;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CarrinhoRepository {

    private final Map<UUID, Carrinho> carrinhos = new HashMap<>();

    public Carrinho salvar(Carrinho carrinho){
        carrinhos.put(carrinho.getIdUsuario(), carrinho);
        return carrinho;
    }

    public Optional<Carrinho> buscarPorUsuario(UUID idUsuario){
        return Optional.ofNullable(carrinhos.get(idUsuario));
    }

    public boolean existePorUsuario(UUID idUsuario){
        return carrinhos.containsKey(idUsuario);
    }

    public void removerPorUsuario(UUID idUsuario){
        carrinhos.remove(idUsuario);
    }
}
