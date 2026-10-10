package br.unicentro.appconsultas.dao.Implementacoes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.unicentro.appconsultas.dao.Interfaces.IConst;

public class Conexao implements IConst {

    public static Connection getConexao() throws SQLException {
        return DriverManager.getConnection(
                stringDeConexao,
                usuario,
                senha
        );
    }
}

