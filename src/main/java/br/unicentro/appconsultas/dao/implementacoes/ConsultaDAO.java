package br.unicentro.appconsultas.dao.implementacoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unicentro.appconsultas.dao.interfaces.IConsulta;
import br.unicentro.appconsultas.model.Consulta;

public class ConsultaDAO implements IConsulta{
    @Override
    public void insere(Consulta consulta) throws SQLException{
        String sql = "INSERT INTO consulta VALUES (?, ?, ?)";
        try (Connection con = Conexao.getConexao();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, consulta.getCpfPaciente());
            stmt.setString(2, consulta.getCrmMedico());
            stmt.setDate(3, java.sql.Date.valueOf(consulta.getData()));
            stmt.executeUpdate();
        }
    }

    @Override
	public void remove(Consulta consulta) throws SQLException{
        String sql = "DELETE FROM consulta WHERE cpfPaciente = ? AND crmMedico = ? AND data = ?";
        try (Connection con = Conexao.getConexao();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, consulta.getCpfPaciente());
            stmt.setString(2, consulta.getCrmMedico());
            stmt.setDate(3, java.sql.Date.valueOf(consulta.getData()));
            stmt.executeUpdate();
        }
    }

    @Override 
	public List<Consulta> busca(String cpfPaciente, String crmMedico, String data) throws SQLException{
        String sql = "SELECT * FROM consulta WHERE ";
        List<Object> strsBusca = new ArrayList<>();
        if(cpfPaciente == null && crmMedico == null && data == null){
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
            strsBusca.add(java.sql.Date.valueOf(data));
        }
        sql=sql.substring(0, sql.length()-5)+" ORDER BY cpfPaciente";
        List<Consulta> consultas = new ArrayList<>();
        
        try (Connection con = Conexao.getConexao();
            PreparedStatement stmt = con.prepareStatement(sql)){
            int i=1;
            for(Object parametro:strsBusca){
                if(parametro instanceof java.sql.Date){
                    stmt.setDate(i, (java.sql.Date) parametro);
                } else {
                    stmt.setString(i, (String) parametro);
                }
                i++;
            }
            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    consultas.add(new Consulta(rs.getString("cpfPaciente"),rs.getString("crmMedico"),rs.getDate("data").toString()));
                }
                
            }
        }
        return consultas;
    }

    @Override
	public List<Consulta> listaTodos() throws SQLException{
        String sql = "SELECT * FROM consulta ORDER BY cpfPaciente";
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
