package br.unicentro.appconsultas.dao.Interfaces;

import java.sql.SQLException;
import java.util.List;

import br.unicentro.appconsultas.model.Consulta;

public interface IConsulta {
    public void insere(Consulta consulta) throws SQLException;

	public void remove(Consulta consulta) throws SQLException;

	public List<Consulta> busca(String cpfPaciente, String crmMedico, String data) throws SQLException;

	public List<Consulta> listaTodos() throws SQLException;
}
