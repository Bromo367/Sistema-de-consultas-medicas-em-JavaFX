package br.unicentro.appconsultas.dao.Interfaces;

import java.sql.SQLException;
import java.util.List;

import br.unicentro.appconsultas.model.Medicamento;

public interface IMedicamento {
    public void insere(Medicamento medicamento) throws SQLException;

	public void atualiza(Medicamento medicamento) throws SQLException;

	public void remove(Medicamento medicamento) throws SQLException;

	public Medicamento buscaPorCodigo(int medicamentoId) throws SQLException;

	public List<Medicamento> listaTodos() throws SQLException;
}
