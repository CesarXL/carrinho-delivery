package com.delivery.carrinhodelivery.controller;

import com.delivery.carrinhodelivery.dto.AdicionarItemDTO;
import com.delivery.carrinhodelivery.dto.AtualizarQuantidadeDTO;
import com.delivery.carrinhodelivery.entity.Carrinho;
import com.delivery.carrinhodelivery.service.CarrinhoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/carrinhos")
public class CarrinhoController {

    private final CarrinhoService carrinhoService;
    public CarrinhoController(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    @PostMapping
    public ResponseEntity<Carrinho> criarCarrinho(
            @RequestParam UUID idUsuario){

        Carrinho carrinho = carrinhoService.criarCarrinho(idUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(carrinho);
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<Carrinho> buscarCarrinho(
            @PathVariable UUID idUsuario){

        Carrinho carrinho = carrinhoService.buscarCarrinho(idUsuario);
        return ResponseEntity.ok(carrinho);

    }

    @PostMapping("/{idUsuario}/itens")
    public ResponseEntity<Carrinho> adicionarItem(
            @PathVariable UUID idUsuario,
            @Valid @RequestBody AdicionarItemDTO dto){

        Carrinho carrinho = carrinhoService.adicionarItem(idUsuario, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(carrinho);
    }

    @PutMapping("/{idUsuario}/itens/{idItem}")
    public ResponseEntity<Carrinho> atualizarQuantidade(
            @PathVariable UUID idUsuario,
            @PathVariable UUID idItem,
            @Valid @RequestBody AtualizarQuantidadeDTO dto){

        Carrinho carrinho = carrinhoService.atualizarQuantidade(
                idUsuario, idItem, dto);

        return ResponseEntity.ok(carrinho);

    }

    @DeleteMapping("/{idUsuario}/itens/{idItem}")
    public ResponseEntity<Carrinho> removerItem(
            @PathVariable UUID idUsuario,
            @PathVariable UUID idItem){

        Carrinho carrinho = carrinhoService.removerItem(idUsuario, idItem);
        return ResponseEntity.ok(carrinho);
    }

    @DeleteMapping("/{idUsuario}/itens")
    public ResponseEntity<Carrinho> esvaziarCarrinho(
            @PathVariable UUID idUsuario){

        Carrinho carrinho = carrinhoService.esvaziarCarrinho(idUsuario);
        return ResponseEntity.ok(carrinho);
    }
}
