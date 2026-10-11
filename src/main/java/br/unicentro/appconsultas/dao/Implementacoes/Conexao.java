package br.unicentro.appconsultas.dao.implementacoes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.unicentro.appconsultas.dao.interfaces.IConst;

public class Conexao implements IConst {

    public static Connection getConexao() throws SQLException {
        return DriverManager.getConnection(
                stringDeConexao,
                usuario,
                senha
        );
    }
}

