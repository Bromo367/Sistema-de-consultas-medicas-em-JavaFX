package br.unicentro.appconsultas.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import br.unicentro.appconsultas.model.Paciente;

public interface IPaciente {
    public void insere(Paciente paciente) throws SQLException;

	public void atualiza(Paciente paciente) throws SQLException;

	public void remove(Paciente paciente) throws SQLException;

	public List<Paciente> busca(int cpfPaciente) throws SQLException;

	public List<Paciente> listaTodos() throws SQLException;
}
