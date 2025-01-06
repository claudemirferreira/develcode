const express = require('express');
const pagamentoController = require('../controllers/pagamentoController');

const router = express.Router();

/**
 * @swagger
 * /pagamento:
 *   post:
 *     summary: Salva um novo pagamento
 *     requestBody:
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             type: object
 *             properties:
 *               pedidoId:
 *                 type: integer
 *               valor:
 *                 type: number
 *               dataPagamento:
 *                 type: string
 *                 format: date
 *     responses:
 *       201:
 *         description: Pagamento salvo com sucesso
 *       400:
 *         description: Dados inválidos
 *       500:
 *         description: Erro interno
 */
router.post('/pagamento', pagamentoController.salvarPagamento);

module.exports = router;
