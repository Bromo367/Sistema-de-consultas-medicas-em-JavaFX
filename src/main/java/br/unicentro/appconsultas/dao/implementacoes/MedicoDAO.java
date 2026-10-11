package br.unicentro.appconsultas.dao.implementacoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unicentro.appconsultas.model.Medico;
import br.unicentro.appconsultas.dao.interfaces.IMedico;

public class MedicoDAO implements IMedico{
    @Override
    public void insere(Medico medico) throws SQLException {
        String sql = "INSERT INTO medico VALUES (?, ?, ?, ?)";
        try (Connection con = Conexao.getConexao();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, medico.getCrmMedico());
            stmt.setString(2, medico.getNome());
            stmt.setString(3, medico.getEspecializacao());
            stmt.setString(4, medico.getTelefone());
            stmt.executeQuery();
        }
    }

    @Override
	public void remove(Medico medico) throws SQLException{
        String sql = "DELETE FROM medico WHERE crmMedico = ?";
        try (Connection con = Conexao.getConexao();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, medico.getCrmMedico());
            stmt.executeUpdate();
        }
    }

    @Override
    public void atualiza(Medico medico) throws SQLException {
        String sql = "UPDATE medico SET nome = ?, especializacao = ?, telefone = ? WHERE crmMedico = ?";
        try (Connection con = Conexao.getConexao();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, medico.getNome());
            stmt.setString(2, medico.getEspecializacao());
            stmt.setString(3, medico.getTelefone());
            stmt.setString(4, medico.getCrmMedico());
            stmt.executeUpdate();
        }
    }

    @Override
    public Medico buscaPorCodigo(String crmMedico) throws SQLException {
        String sql = "SELECT * FROM medico WHERE crmMedico = ?";
        try (Connection con = Conexao.getConexao();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, crmMedico);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nome = rs.getString("nome");
                    String especializacao = rs.getString("especializacao");
                    String telefone = rs.getString("telefone");
                    Medico medico = new Medico(crmMedico, nome, especializacao, telefone);
                    return medico;
                } 
            }
        }
        return null;
    }

    @Override
	public List<Medico> listaTodos() throws SQLException{
        String sql = "SELECT * FROM medico ORDER BY crmMedico";
        List<Medico> medicos = new ArrayList<>();

        try (Connection con = Conexao.getConexao();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String crmMedico = rs.getString("crmMedico");
                String nome = rs.getString("nome");
                String especializacao = rs.getString("especializacao");
                String telefone = rs.getString("telefone");
                medicos.add(new Medico(crmMedico, nome, especializacao, telefone));
            }
        }
        return medicos;
    }

}
