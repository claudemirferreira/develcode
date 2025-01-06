// src/__tests__/controllers/salvarPagamento.test.js
const express = require('express');
const request = require('supertest');
const { salvarPagamento } = require('../../controllers/pagamentoController'); // Caminho correto do seu controlador
const pagamentoModel = require('../../models/pagamentoModel'); // Caminho correto do seu modelo
const { validatePagamento } = require('../../utils/validations');

// Mock do pagamentoModel
jest.mock('../../models/pagamentoModel');

// Criação do app express para testar a rota
const app = express();
app.use(express.json()); // Middleware para parsear o JSON
app.post('/pagamento', salvarPagamento); // Rota que usa o controlador salvarPagamento

describe('POST /pagamento', () => {
  it('deve salvar um pagamento com dados válidos e retornar 201', async () => {
    // Mock da função createPagamento
    const novoPagamento = {
      pedidoId: 1,
      valor: 100.0,
      dataPagamento: '2025-01-01T12:00:00Z',
    };
    pagamentoModel.createPagamento.mockResolvedValue({
      id: 1,
      ...novoPagamento,
    });

    // Dados válidos para a requisição
    const pagamentoValido = {
      pedidoId: 1,
      valor: 100.0,
      dataPagamento: '2025-01-01T12:00:00Z',
    };

    const response = await request(app)
      .post('/pagamento')
      .send(pagamentoValido)
      .expect('Content-Type', /json/)
      .expect(201);

    expect(response.body).toHaveProperty('id');
    expect(response.body.pedidoId).toBe(pagamentoValido.pedidoId);
    expect(response.body.valor).toBe(pagamentoValido.valor);
  });

  it('deve retornar 400 se os dados de pagamento forem inválidos', async () => {
    // Dados inválidos para a requisição
    const pagamentoInvalido = {
      pedidoId: '1', // Valor inválido, pois deve ser um número inteiro
      valor: -100.0,  // Valor inválido, pois deve ser positivo
      dataPagamento: '2025-01-01', // Formato de data inválido
    };

    const response = await request(app)
      .post('/pagamento')
      .send(pagamentoInvalido)
      .expect('Content-Type', /json/)
      .expect(400);

    expect(response.body.error).toBeDefined();
  });

  it('deve retornar 500 em caso de erro interno do servidor', async () => {
    // Simula erro ao tentar salvar no banco
    pagamentoModel.createPagamento.mockRejectedValue(new Error('Erro ao salvar pagamento'));

    const pagamentoValido = {
      pedidoId: 1,
      valor: 100.0,
      dataPagamento: '2025-01-01T12:00:00Z',
    };

    const response = await request(app)
      .post('/pagamento')
      .send(pagamentoValido)
      .expect('Content-Type', /json/)
      .expect(500);

    expect(response.body.error).toBe('Erro interno do servidor');
  });
});
