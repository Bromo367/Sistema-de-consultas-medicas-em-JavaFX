package br.unicentro.appconsultas.dao.implementacoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unicentro.appconsultas.dao.interfaces.IReceita;
import br.unicentro.appconsultas.model.Receita;

public class ReceitaDAO implements IReceita{
    @Override
    public void insere(Receita receita) throws SQLException {
        String sql = "INSERT INTO receita VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexao.getConexao();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, receita.getCpfPaciente());
            stmt.setString(2, receita.getCrmMedico());
            stmt.setString(3, receita.getData());
            stmt.setInt(4, receita.getMedicamentoId());
            stmt.setDouble(5, receita.getDose());
            stmt.setString(6, receita.getUnidadeMedida());
            stmt.setString(7, receita.getIntervalo());
            stmt.executeQuery();
        }
    }

    @Override
	public void remove(Receita receita) throws SQLException{
        String sql = "DELETE FROM receita WHERE cpfPaciente = ? AND crmMedico = ? AND data = ? AND medicamentoId = ?";
        try (Connection con = Conexao.getConexao();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, receita.getCpfPaciente());
            stmt.setString(2, receita.getCrmMedico());
            stmt.setString(3, receita.getData());
            stmt.setInt(4, receita.getMedicamentoId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void atualiza(Receita receita) throws SQLException {
        String sql = "UPDATE receita SET dose = ?, unidadeMedida = ?, intervalo = ? WHERE cpfPaciente = ? AND crmMedico = ? AND data = ? AND medicamentoId = ?";
        try (Connection con = Conexao.getConexao();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setDouble(1, receita.getDose());
            stmt.setString(2, receita.getUnidadeMedida());
            stmt.setString(3, receita.getIntervalo());
            stmt.setString(4, receita.getCpfPaciente());
            stmt.setString(5, receita.getCrmMedico());
            stmt.setString(6, receita.getData());
            stmt.setInt(7, receita.getMedicamentoId());
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Receita> busca(String cpfPaciente, String crmMedico, String data, int medicamentoId) throws SQLException {
        String sql = "SELECT * FROM estudante WHERE ";
        List<String> strsBusca = new ArrayList<>();
        if(cpfPaciente == null && crmMedico == null && data == null && medicamentoId < 0){
            return listaTodos();
        }
        if(cpfPaciente!=null){
            sql=sql+"cpfPaciente = ? AND ";
            strsBusca.add(cpfPaciente);
        }
        if(crmMedico!=null){
            sql=sql+"crmMedico = ? AND ";
            strsBusca.add(crmMedico);
        }
        if(data!=null){
            sql=sql+"data = ? AND ";
            strsBusca.add(data);
        }
        if(medicamentoId>=0){
            sql=sql+"medicamentoId = ? AND ";
        }
        sql=sql.substring(0, sql.length()-5)+" ORDER BY cpfPaciente";
        List<Receita> receitas = new ArrayList<>();
        try (Connection con = Conexao.getConexao();
            PreparedStatement stmt = con.prepareStatement(sql)){
            int i=1;
            for(String str:strsBusca){
                stmt.setString(i,str);
                i++;
            }
            if(medicamentoId>=0){
                stmt.setInt(i,medicamentoId);
            }
            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String resultadoCpfPaciente = rs.getString("cpfPaciente");  
                    String resultadoCrmMedico = rs.getString("crmMedico");    
                    String resultadoData = rs.getString("data");         
                    int resultadoMedicamentoId = rs.getInt("medicamentoId");   
                    double dose = rs.getDouble("dose");         
                    String unidadeMedida = rs.getString("unidadeMedida");
                    String intervalo = rs.getString("intervalo");    
                    receitas.add(new Receita(resultadoCpfPaciente, resultadoCrmMedico, resultadoData, resultadoMedicamentoId, dose, unidadeMedida, intervalo));
                }
            }
        }
        return receitas;
    }

    @Override
	public List<Receita> listaTodos() throws SQLException{
        String sql = "SELECT * FROM receita ORDER BY cpfPaciente";
        List<Receita> receitas = new ArrayList<>();

        try (Connection con = Conexao.getConexao();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String cpfPaciente = rs.getString("cpfPaciente");  
                String crmMedico = rs.getString("crmMedico");    
                String data = rs.getString("data");         
                int medicamentoId = rs.getInt("medicamentoId");   
                double dose = rs.getDouble("dose");         
                String unidadeMedida = rs.getString("unidadeMedida");
                String intervalo = rs.getString("intervalo");    
                receitas.add(new Receita(cpfPaciente, crmMedico, data, medicamentoId, dose, unidadeMedida, intervalo));           
            }
        }
        return receitas;
    }
}