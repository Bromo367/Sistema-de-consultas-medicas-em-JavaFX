package br.unicentro.appestudantes.dao.Interfaces;

import java.sql.SQLException;
import java.util.List;

import br.unicentro.appestudantes.model.Medico;

public interface IMedico {
    public void insere(Medico medico) throws SQLException;

	public void atualiza(Medico medico) throws SQLException;

	public void remove(Medico medico) throws SQLException;

	public List<Medico> busca(int crmMedico) throws SQLException;

	public List<Medico> listaTodos() throws SQLException;
}
