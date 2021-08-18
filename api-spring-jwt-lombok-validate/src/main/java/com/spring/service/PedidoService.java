package com.spring.service;

import java.util.Optional;

import com.spring.domain.entity.Pedido;
import com.spring.domain.enums.StatusPedido;
import com.spring.rest.dto.PedidoDTO;

public interface PedidoService {
    Pedido salvar( PedidoDTO dto );
    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
