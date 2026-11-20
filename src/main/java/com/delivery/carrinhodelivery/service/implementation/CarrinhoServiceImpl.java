package com.delivery.carrinhodelivery.service.implementation;

import com.delivery.carrinhodelivery.client.CatalogoClient;
import com.delivery.carrinhodelivery.dto.AdicionarItemDTO;
import com.delivery.carrinhodelivery.dto.AtualizarQuantidadeDTO;
import com.delivery.carrinhodelivery.dto.ProdutoCatalogoDTO;
import com.delivery.carrinhodelivery.entity.Carrinho;
import com.delivery.carrinhodelivery.entity.ItemCarrinho;
import com.delivery.carrinhodelivery.exception.CarrinhoJaExisteException;
import com.delivery.carrinhodelivery.exception.CarrinhoNaoEncontradoException;
import com.delivery.carrinhodelivery.exception.ItemNaoEncontradoException;
import com.delivery.carrinhodelivery.exception.ProdutoNaoEncontradoException;
import com.delivery.carrinhodelivery.repository.CarrinhoRepository;
import com.delivery.carrinhodelivery.service.CarrinhoService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class CarrinhoServiceImpl implements CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final CatalogoClient catalogoClient;

    public CarrinhoServiceImpl(
            CarrinhoRepository carrinhoRepository,
            CatalogoClient catalogoClient) {
        this.carrinhoRepository = carrinhoRepository;
        this.catalogoClient = catalogoClient;
    }

    @Override
    public Carrinho buscarCarrinho(UUID idUsuario) {
        return carrinhoRepository.buscarPorUsuario(idUsuario)
                .orElseThrow(() -> new CarrinhoNaoEncontradoException(
                        "Carrinho não encontrado!"
                ));
    }

    @Override
    public Carrinho criarCarrinho(UUID idUsuario) {

        if (carrinhoRepository.existePorUsuario(idUsuario)) {
            throw new CarrinhoJaExisteException(
                    "Usuário já possui um carrinho!"
            );
        }

        Carrinho carrinho = new Carrinho();

        LocalDateTime agora = LocalDateTime.now();

        carrinho.setIdCarrinho(UUID.randomUUID());
        carrinho.setIdUsuario(idUsuario);
        carrinho.setDataCriacao(agora);
        carrinho.setDataAtualizacao(agora);
        carrinho.setItens(new ArrayList<>());

        return carrinhoRepository.salvar(carrinho);
    }

    @Override
    public Carrinho adicionarItem(UUID idUsuario, AdicionarItemDTO dto) {

        Carrinho carrinho = buscarCarrinho(idUsuario);

        ProdutoCatalogoDTO produto = catalogoClient.buscarProduto(dto.idProduto());

        if (produto == null) {
            throw new ProdutoNaoEncontradoException(
                    "Produto não encontrado no Catálogo!"
            );
        }

        if (produto.precoCentavos() == null || produto.precoCentavos() <= 0) {
            throw new IllegalArgumentException(
                    "Preço inválido!"
            );
        }

        ItemCarrinho itemExistente = carrinho.getItens().stream()
                .filter(item -> item.getIdProduto().equals(dto.idProduto()))
                .findFirst()
                .orElse(null);

        if (itemExistente != null) {
            itemExistente.setPrecoUnitarioCentavos(produto.precoCentavos());
            itemExistente.setNomeProduto(produto.nome());
            itemExistente.setQuantidade(
                    Math.addExact(itemExistente.getQuantidade(), dto.quantidade())
            );
        } else {

            ItemCarrinho novoItem = new ItemCarrinho();

            novoItem.setIdItem(UUID.randomUUID());
            novoItem.setIdProduto(produto.idProduto());
            novoItem.setNomeProduto(produto.nome());
            novoItem.setPrecoUnitarioCentavos(produto.precoCentavos());
            novoItem.setQuantidade(dto.quantidade());

            carrinho.getItens().add(novoItem);
        }

        carrinho.setDataAtualizacao(LocalDateTime.now());

        return carrinhoRepository.salvar(carrinho);
    }

    @Override
    public Carrinho atualizarQuantidade(
            UUID idUsuario,
            UUID idItem,
            AtualizarQuantidadeDTO dto) {

        Carrinho carrinho = buscarCarrinho(idUsuario);

        ItemCarrinho item = carrinho.getItens().stream()
                .filter(i -> i.getIdItem().equals(idItem))
                .findFirst()
                .orElseThrow(() -> new ItemNaoEncontradoException(
                        "Item não encontrado no carrinho!"
                ));

        item.setQuantidade(dto.quantidade());

        carrinho.setDataAtualizacao(LocalDateTime.now());

        return carrinhoRepository.salvar(carrinho);
    }

    @Override
    public Carrinho removerItem(UUID idUsuario, UUID idItem) {

        Carrinho carrinho = buscarCarrinho(idUsuario);

        ItemCarrinho item = carrinho.getItens().stream()
                .filter(i -> i.getIdItem().equals(idItem))
                .findFirst()
                .orElseThrow(() -> new ItemNaoEncontradoException(
                        "Item não  encontrado no carrinho!"
                ));

        carrinho.getItens().remove(item);

        carrinho.setDataAtualizacao(LocalDateTime.now());

        return carrinhoRepository.salvar(carrinho);
    }


    @Override
    public Carrinho esvaziarCarrinho(UUID idUsuario) {

        Carrinho carrinho = buscarCarrinho(idUsuario);

        carrinho.getItens().clear();
        carrinho.setDataAtualizacao(LocalDateTime.now());

        return carrinhoRepository.salvar(carrinho);
    }
}
