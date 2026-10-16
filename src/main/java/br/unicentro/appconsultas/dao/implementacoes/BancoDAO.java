package br.unicentro.appconsultas.dao.implementacoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.unicentro.appconsultas.dao.interfaces.IBanco;

public class BancoDAO implements IBanco{
    public void apagarTudo() throws SQLException{
        String sql = "DELETE * FROM *";
        try (Connection con = Conexao.getConexao();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.executeQuery();
        }
    }
}
