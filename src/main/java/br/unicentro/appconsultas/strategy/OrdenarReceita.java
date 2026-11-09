package br.unicentro.appconsultas.strategy;

import java.util.List;
import br.unicentro.appconsultas.model.Receita;

public interface OrdenarReceita {
    void ordenar(List<Receita> receitas);
}
