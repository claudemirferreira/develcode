const db = require('../config/db');

const createPagamento = async (pagamento) => {
    const { pedidoId, valor, dataPagamento } = pagamento;
    const [result] = await db.query(
        'INSERT INTO pagamento ( pedido_id, valor_pago, data_pagamento) VALUES ( ?, ?, ?)',
        [ pedidoId, valor, dataPagamento]
    );
    return { id: result.insertId, ...pagamento };
};

module.exports = { createPagamento };
