package br.unicentro.appconsultas.dao.implementacoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unicentro.appconsultas.dao.interfaces.IMedicamento;
import br.unicentro.appconsultas.model.Medicamento;

public class MedicamentoDAO implements IMedicamento{
    @Override
    public void insere(Medicamento medicamento) throws SQLException {
        String sql = "INSERT INTO medico (nome) VALUES (?) RETURNING medicamentoId";
        try (Connection con = Conexao.getConexao();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, medicamento.getNome());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    medicamento.setMedicamentoId(rs.getInt("medicamentoId"));
                }
            }
        }
    }

    @Override
	public void remove(Medicamento medicamento) throws SQLException{
        String sql = "DELETE FROM medicamento WHERE medicamentoId = ?";
        try (Connection con = Conexao.getConexao();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, medicamento.getMedicamentoId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void atualiza(Medicamento medicamento) throws SQLException {
        String sql = "UPDATE medicamento SET nome = ? WHERE medicamentoId = ?";
        try (Connection con = Conexao.getConexao();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, medicamento.getNome());
            stmt.setInt(2, medicamento.getMedicamentoId());
            stmt.executeUpdate();
        }
    }

    @Override
    public Medicamento buscaPorCodigo(int medicamentoId) throws SQLException {
        String sql = "SELECT * FROM medicamento WHERE medicamentoId = ?";
        try (Connection con = Conexao.getConexao();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, medicamentoId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nome = rs.getString("nome");
                    Medicamento medicamento = new Medicamento(medicamentoId,nome);
                    return medicamento;
                } 
            }
        }
        return null;
    }

    @Override
	public List<Medicamento> listaTodos() throws SQLException{
        String sql = "SELECT * FROM medicamento ORDER BY medicamentoId";
        List<Medicamento> medicamentos = new ArrayList<>();

        try (Connection con = Conexao.getConexao();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                medicamentos.add(new Medicamento(rs.getInt("medicamentoId"),rs.getString("nome")));
            }
        }
        return medicamentos;
    }

}
