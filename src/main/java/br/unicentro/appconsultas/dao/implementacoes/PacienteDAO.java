package br.unicentro.appconsultas.dao.implementacoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unicentro.appconsultas.dao.interfaces.IPaciente;
import br.unicentro.appconsultas.model.Paciente;

public class PacienteDAO implements IPaciente{
    @Override
    public void insere(Paciente paciente) throws SQLException {
        String sql = "INSERT INTO paciente VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexao.getConexao();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, paciente.getCpfPaciente());
            stmt.setString(2, paciente.getNome());
            stmt.setInt(3, paciente.getIdade());
            stmt.setDouble(4, paciente.getPeso());
            stmt.setDouble(5, paciente.getAltura());
            stmt.setString(6, paciente.getTelefone());
            stmt.executeUpdate();
        }
    }

    @Override
	public void remove(Paciente paciente) throws SQLException{
        String sql = "DELETE FROM paciente WHERE cpfPaciente = ?";
        try (Connection con = Conexao.getConexao();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, paciente.getCpfPaciente());
            stmt.executeUpdate();
        }
    }

    @Override
    public void atualiza(Paciente paciente) throws SQLException {
        String sql = "UPDATE paciente SET nome = ?, idade = ?, peso = ?, altura = ?, telefone = ? WHERE cpfPaciente = ?";
        try (Connection con = Conexao.getConexao();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, paciente.getNome());
            stmt.setInt(2, paciente.getIdade());
            stmt.setDouble(3, paciente.getPeso());
            stmt.setDouble(4, paciente.getAltura());
            stmt.setString(5, paciente.getTelefone());
            stmt.setString(6, paciente.getCpfPaciente());
            stmt.executeUpdate();
        }
    }

    @Override
    public Paciente buscaPorCodigo(String cpfPaciente) throws SQLException {
        String sql = "SELECT * FROM paciente WHERE cpfPaciente = ?";
        try (Connection con = Conexao.getConexao();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, cpfPaciente);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nome = rs.getString("nome");
                    int idade = rs.getInt("idade");      
                    double peso = rs.getDouble("peso");    
                    double altura = rs.getDouble("altura");  
                    String telefone = rs.getString("telefone");
                    Paciente paciente = new Paciente(cpfPaciente, nome , idade, peso, altura, telefone);
                    return paciente;
                } 
            }
        }
        return null;
    }

    @Override
	public List<Paciente> listaTodos() throws SQLException{
        String sql = "SELECT * FROM paciente ORDER BY cpfPaciente";
        List<Paciente> pacientes = new ArrayList<>();

        try (Connection con = Conexao.getConexao();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String cpfPaciente = rs.getString("cpfPaciente");
                String nome = rs.getString("nome");
                int idade = rs.getInt("idade");      
                double peso = rs.getDouble("peso");    
                double altura = rs.getDouble("altura");  
                String telefone = rs.getString("telefone");
                pacientes.add(new Paciente(cpfPaciente, nome , idade, peso, altura, telefone));
            }
        }
        return pacientes;
    }
}
