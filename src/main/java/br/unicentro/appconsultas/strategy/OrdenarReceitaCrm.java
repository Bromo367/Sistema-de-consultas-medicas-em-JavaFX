package br.unicentro.appconsultas.strategy;

import java.util.List;
import br.unicentro.appconsultas.model.Receita;

public class OrdenarReceitaCrm implements OrdenarReceita{
    
    @Override
    public void ordenar(List<Receita> receitas){
        receitas.sort((r1, r2) -> r1.getCrmMedico().compareTo(r2.getCrmMedico()));
    }
}