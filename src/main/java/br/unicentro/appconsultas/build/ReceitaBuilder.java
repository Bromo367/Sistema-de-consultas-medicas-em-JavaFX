package br.unicentro.appconsultas.build;


import br.unicentro.appconsultas.model.Medico;
import br.unicentro.appconsultas.model.Receita;

public class ReceitaBuilder{
    private String cpfPaciente;
    private String crmMedico;
    private String data;
    private int medicamentoId;
    private double dose;
    private String unidadeMedida;
    private String intervalo;

    public ReceitaBuilder cpfPaciente(String cpf){
        this.cpfPaciente = cpf;
        return this;
    }

    public ReceitaBuilder crmMedico(String crm){
        this.crmMedico = crm;
        return this;
    }
    public ReceitaBuilder data(String data){
        this.data = data;
        return this;
    }

    public ReceitaBuilder medicamentoId(int id){
        this.medicamentoId = id;
        return this;
    }

    public ReceitaBuilder dose(Double dose){
        this.dose = dose;
        return this;
    }

    public ReceitaBuilder unidadeMedida(String unidadeMedida){
        this.unidadeMedida = unidadeMedida;
        return this;
    }
    public ReceitaBuilder intervalo(String intervalo){
        this.intervalo = intervalo;
        return this;
    }

    public Receita build(){
        return new Receita(cpfPaciente,crmMedico,data, medicamentoId, dose, unidadeMedida, intervalo);
    }
}