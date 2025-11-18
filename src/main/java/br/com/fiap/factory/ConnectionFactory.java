package br.com.fiap.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    // Substitua pelas credenciais do seu banco Oracle
    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    private static final String USER = "RM564662";
    private static final String PASS = "020207";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        // Carrega o driver (opcional em versoes novas do Java, mas bom garantir)
        Class.forName("oracle.jdbc.driver.OracleDriver");
        return DriverManager.getConnection(URL, USER, PASS);
    }
}