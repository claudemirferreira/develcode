const mysql = require('mysql2/promise');

const pool = mysql.createPool({
    host: 'localhost',
    user: 'develcode',
    password: 'develcode',
    database: 'develcode',
});

module.exports = pool;
