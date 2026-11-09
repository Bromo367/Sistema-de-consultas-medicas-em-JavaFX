package br.unicentro.appconsultas.observer;

import java.util.List;
import br.unicentro.appconsultas.model.Receita;

public interface ReceitaObserver {
    void listaReceitaAtualizada(List<Receita> receitas);
}
