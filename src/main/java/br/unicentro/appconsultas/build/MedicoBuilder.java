package br.unicentro.appconsultas.build;

import br.unicentro.appconsultas.model.Medico;

public class MedicoBuilder{
    private String crmMedico;
    private String nome;
    private String especializacao;
    private String telefone;

    public MedicoBuilder crmMedico(String crm){
        this.crmMedico = crm;
        return this;
    }
    public MedicoBuilder nome(String nome){
        this.nome = nome;
        return this;
    }
    public MedicoBuilder especializacao(String especializacao){
        this.especializacao = especializacao;
        return this;
    }
    public MedicoBuilder telefone(String telefone){
        this.telefone = telefone;
        return this;
    }

    public Medico build(){
        return new Medico(crmMedico,nome,especializacao,telefone);
    }
}