package br.unicentro.appconsultas.dao.interfaces;

import java.sql.SQLException;

import br.unicentro.appconsultas.model.Estudante;

public interface IEstudante {

    void inserir(Estudante estudante) throws SQLException;

    public Estudante buscaPorCodigo(int estudanteID) throws SQLException;
}