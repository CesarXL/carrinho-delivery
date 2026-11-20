package com.delivery.carrinhodelivery.exception.handler;

import com.delivery.carrinhodelivery.dto.ErroResponseDTO;
import com.delivery.carrinhodelivery.entity.ItemCarrinho;
import com.delivery.carrinhodelivery.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CarrinhoNaoEncontradoException.class)
    public ResponseEntity<ErroResponseDTO> tratarCarrinhoNaoEncontrado(
            CarrinhoNaoEncontradoException ex){

        ErroResponseDTO erro = new ErroResponseDTO(true, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(CarrinhoJaExisteException.class)
    public ResponseEntity<ErroResponseDTO> tratarCarrinhoJaExiste(
            CarrinhoJaExisteException ex){

        ErroResponseDTO erro = new ErroResponseDTO(true, ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(ItemNaoEncontradoException.class)
    public ResponseEntity<ErroResponseDTO> tratarItemNaoEncontrado(
            ItemNaoEncontradoException ex){

        ErroResponseDTO erro = new ErroResponseDTO(true, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ResponseEntity<ErroResponseDTO> tratarProdutoNaoEncontrado(
            ProdutoNaoEncontradoException ex){

        ErroResponseDTO erro = new ErroResponseDTO(true, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(CatalogoIndisponivelException.class)
    public ResponseEntity<ErroResponseDTO> tratarCatalogoIndisponivel(
            CatalogoIndisponivelException ex){

        ErroResponseDTO erro = new ErroResponseDTO(true, ex.getMessage());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(erro);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErroResponseDTO> tratarArgumentoInvalido(
            IllegalArgumentException ex){

        ErroResponseDTO erro = new ErroResponseDTO(true, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}
