const express = require('express');
const bodyParser = require('body-parser');
const pagamentoRoutes = require('./routes/pagamentoRoutes');
const swaggerUi = require('swagger-ui-express');
const swaggerDocument = require('./swagger/swagger.json');

const app = express();
app.use(bodyParser.json());
app.use('/api-docs', swaggerUi.serve, swaggerUi.setup(swaggerDocument));
app.use('/', pagamentoRoutes);

module.exports = app;
