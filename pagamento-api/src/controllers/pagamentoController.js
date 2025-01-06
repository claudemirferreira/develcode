const pagamentoModel = require('../models/pagamentoModel');
const { validatePagamento } = require('../utils/validations');

const salvarPagamento = async (req, res) => {
    try {
        const pagamento = req.body;

        // Validação dos dados
        const { error } = validatePagamento(pagamento);
        if (error) return res.status(400).json({ error: error.details[0].message });

        // Converter dataPagamento para o formato esperado pelo MySQL
        const dataPagamento = new Date(pagamento.dataPagamento)
            .toISOString()
            .replace('T', ' ')
            .split('.')[0]; // Remove frações de segundo

        // Atualizar o objeto antes de salvar
        pagamento.dataPagamento = dataPagamento;

        // Salvar pagamento no banco
        const novoPagamento = await pagamentoModel.createPagamento(pagamento);
        return res.status(201).json(novoPagamento);
    } catch (err) {
        console.error(err);
        return res.status(500).json({ error: 'Erro interno do servidor' });
    }
};

module.exports = { salvarPagamento };
