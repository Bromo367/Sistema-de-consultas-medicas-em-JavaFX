package br.unicentro.appconsultas.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import br.unicentro.appconsultas.model.Medico;

public interface IMedico {
    public void insere(Medico medico) throws SQLException;

	public void atualiza(Medico medico) throws SQLException;

	public void remove(Medico medico) throws SQLException;

	public Medico buscaPorCodigo(String crmMedico) throws SQLException;

	public List<Medico> listaTodos() throws SQLException;
}
