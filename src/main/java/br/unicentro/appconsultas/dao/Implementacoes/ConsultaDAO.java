package br.unicentro.appconsultas.dao.Implementacoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unicentro.appconsultas.dao.Interfaces.IConsulta;
import br.unicentro.appconsultas.model.Consulta;

public class ConsultaDAO implements IConsulta{
    @Override
    public void insere(Consulta consulta) throws SQLException{
        String sql = "INSERT INTO consulta VALUES (?, ?, ?)";
        try (Connection con = Conexao.getConexao();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, consulta.getCpfPaciente());
            stmt.setString(2, consulta.getCrmMedico());
            stmt.setString(3, consulta.getData());
        }
    }

    @Override
	public void remove(Consulta consulta) throws SQLException{
        String sql = "DELETE FROM consulta WHERE cpfPaciente = ? AND crmMedico = ? AND data = ?";
        try (Connection con = Conexao.getConexao();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, consulta.getCpfPaciente());
            stmt.setString(2, consulta.getCrmMedico());
            stmt.setString(3, consulta.getData());
            stmt.executeUpdate();
        }
    }

    @Override 
	public List<Consulta> busca(int cpfPaciente, int crmMedico, String data) throws SQLException{
        String sql = "SELECT * FROM estudante ORDER BY estudante_id";
        List<Consulta> consultas = new ArrayList<>();

        try (Connection con = Conexao.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
             while (rs.next()) {
                consultas.add(new Consulta(rs.getString("cpfPaciente"),rs.getString("crmMedico"),rs.getString("data")));
             }
        }
        return consultas;
    
    }

    @Override
	public List<Consulta> listaTodos() throws SQLException{
        String sql = "SELECT * FROM estudante ORDER BY estudante_id";
        List<Consulta> consultas = new ArrayList<>();

        try (Connection con = Conexao.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
             while (rs.next()) {
                consultas.add(new Consulta(rs.getString("cpfPaciente"),rs.getString("crmMedico"),rs.getString("data")));
             }
        }
        return consultas;
    }
}
