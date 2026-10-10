package br.unicentro.appconsultas.dao.implementacoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import br.unicentro.appconsultas.dao.interfaces.IEstudante;
import br.unicentro.appconsultas.model.Estudante;

public class EstudanteDAO implements IEstudante {

    @Override
    public void inserir(Estudante estudante) throws SQLException {

        String sql =
                "INSERT INTO estudante (nome, idade) " +
                "VALUES (?, ?) RETURNING estudante_id";

        try (Connection conexao = Conexao.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, estudante.getNome());

            if (estudante.getIdade() == null) {
                stmt.setNull(2, Types.INTEGER);
            } else {
                stmt.setInt(2, estudante.getIdade());
            }

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    estudante.setEstudanteID(
                            rs.getInt("estudante_id")
                    );
                }
            }
        }
    }

     @Override
    public Estudante buscaPorCodigo(int estudanteID) throws SQLException {
        String sql = "SELECT * FROM estudante WHERE estudante_id = ?";
        try (Connection con = Conexao.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {
             stmt.setInt(1, estudanteID);
             try (ResultSet rs = stmt.executeQuery()) {
                 if (rs.next()) {
                    Estudante estudante = new Estudante();
                    estudante.setEstudanteID(rs.getInt("estudante_id"));
                    estudante.setNome(rs.getString("nome"));
                    estudante.setIdade(rs.getInt("idade"));
                    return estudante;
                 }
             }
         }
         return null;
    }

}
