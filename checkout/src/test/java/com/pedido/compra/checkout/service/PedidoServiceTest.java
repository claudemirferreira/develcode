package com.pedido.compra.checkout.service;

import com.pedido.compra.checkout.entity.Pedido;
import com.pedido.compra.checkout.enums.StatusPedidoEnum;
import com.pedido.compra.checkout.exception.NotFoundException;
import com.pedido.compra.checkout.repository.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoService pedidoService;

    private Pedido pedido;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Setup inicial do pedido
        pedido = new Pedido();
        pedido.setId(1L);
        pedido.setStatus(StatusPedidoEnum.PENDENTE);
        pedido.setData(OffsetDateTime.now());
    }

    @Test
    public void testCriarPedido() {
        // Arrange
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);

        // Act
        Pedido pedidoCriado = pedidoService.criar(pedido);

        // Assert
        assertNotNull(pedidoCriado);
        assertEquals(StatusPedidoEnum.PENDENTE, pedidoCriado.getStatus());
        assertNotNull(pedidoCriado.getData()); // Verifica se a data foi setada
        verify(pedidoRepository, times(1)).save(any(Pedido.class)); // Verifica se save foi chamado uma vez
    }

    @Test
    public void testBuscarTodosPedidos() {
        // Arrange
        when(pedidoRepository.findAll()).thenReturn(List.of(pedido));

        // Act
        List<Pedido> pedidos = pedidoService.buscarTodos();

        // Assert
        assertNotNull(pedidos);
        assertFalse(pedidos.isEmpty());
        assertEquals(1, pedidos.size());
        verify(pedidoRepository, times(1)).findAll(); // Verifica se findAll foi chamado uma vez
    }

    @Test
    public void testBuscarPorIdPedidoExistente() {
        // Arrange
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        // Act
        Pedido pedidoBuscado = pedidoService.buscarPorId(1L);

        // Assert
        assertNotNull(pedidoBuscado);
        assertEquals(1L, pedidoBuscado.getId());
        verify(pedidoRepository, times(1)).findById(1L); // Verifica se findById foi chamado uma vez
    }

    @Test
    public void testBuscarPorIdPedidoNaoExistente() {
        // Arrange
        when(pedidoRepository.findById(2L)).thenReturn(Optional.empty());

        // Act & Assert
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> {
            pedidoService.buscarPorId(2L);
        });
        assertEquals("não encountrou o pedido 2", thrown.getMessage());
        verify(pedidoRepository, times(1)).findById(2L); // Verifica se findById foi chamado uma vez
    }
}
