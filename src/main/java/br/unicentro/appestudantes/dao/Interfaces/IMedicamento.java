package br.unicentro.appestudantes.dao.Interfaces;

import java.sql.SQLException;
import java.util.List;

import br.unicentro.appestudantes.model.Medicamento;

public interface IMedicamento {
    public void insere(Medicamento medicamento) throws SQLException;

	public void atualiza(Medicamento medicamento) throws SQLException;

	public void remove(Medicamento medicamento) throws SQLException;

	public List<Medicamento> busca(int medicamentoId) throws SQLException;

	public List<Medicamento> listaTodos() throws SQLException;
}
