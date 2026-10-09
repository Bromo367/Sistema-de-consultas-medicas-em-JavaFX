package br.unicentro.appestudantes.dao.Interfaces;

import java.sql.SQLException;
import java.util.List;

import br.unicentro.appestudantes.model.Consulta;

public interface IConsulta {
    public void insere(Consulta consulta) throws SQLException;

	public void atualiza(Consulta consulta) throws SQLException;

	public void remove(Consulta consulta) throws SQLException;

	public List<Consulta> busca(int cpfPaciente, int crmMedico, String data) throws SQLException;

	public List<Consulta> listaTodos() throws SQLException;
}
