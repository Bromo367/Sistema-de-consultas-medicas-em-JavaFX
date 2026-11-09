package br.unicentro.appconsultas.strategy;

import java.util.List;
import java.time.LocalDate;
import br.unicentro.appconsultas.model.Receita;

public class OrdenarReceitaData implements OrdenarReceita{
    
    @Override
    public void ordenar(List<Receita> receitas){
        receitas.sort((r1, r2) -> LocalDate.parse(r1.getCrmMedico()).compareTo(LocalDate.parse(r2.getCrmMedico())));
    }
}