package br.unicentro.appconsultas.observer;

import java.util.List;
import br.unicentro.appconsultas.model.Receita;
import br.unicentro.appconsultas.strategy.OrdenarReceita;
import javafx.scene.control.TableView;

public class ReceitaObserverConcrete implements ReceitaObserver{
    private TableView<Receita> tabela;
    private OrdenarReceita metodo;

    public ReceitaObserverConcrete(TableView<Receita> tabela) {
        this.tabela = tabela;
    }

    public void setMetodo(OrdenarReceita metodo) {
        this.metodo = metodo;
    }

    @Override
    public void listaReceitaAtualizada(List<Receita> receitas) {
        
        if(metodo != null){
            metodo.ordenar(receitas);
        }
        tabela.getItems().setAll(receitas);

        double altura = 28 + (tabela.getItems().size() * 24) + 5;
        tabela.setPrefHeight(altura);
        tabela.setMinHeight(altura);
        tabela.setMaxHeight(altura);
    }
}
