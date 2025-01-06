const Joi = require('joi');

const validatePagamento = (pagamento) => {
    const schema = Joi.object({
        pedidoId: Joi.number().integer().required(),
        valor: Joi.number().positive().required(),
        dataPagamento: Joi.date().iso().required(), // Aceita formato ISO 8601
    });
    return schema.validate(pagamento);
};

module.exports = { validatePagamento };
