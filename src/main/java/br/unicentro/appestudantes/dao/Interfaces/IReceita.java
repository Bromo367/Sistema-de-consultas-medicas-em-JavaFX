package br.unicentro.appestudantes.dao.Interfaces;

import java.sql.SQLException;
import java.util.List;

import br.unicentro.appestudantes.model.Receita;

public interface IReceita {
    public void insere(Receita receita) throws SQLException;

	public void atualiza(Receita receita) throws SQLException;

	public void remove(Receita receita) throws SQLException;

	public List<Receita> busca(int medicamentoId, int cpfPaciente, int crmMedico, String data) throws SQLException;

	public List<Receita> listaTodos() throws SQLException;
}
