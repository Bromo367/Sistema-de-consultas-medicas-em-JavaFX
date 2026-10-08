package br.unicentro.appestudantes.model;

public class Medico {
    private String crmMedico;
    private String nome;
    private String especializacao;
    private String telefone;
    public Medico(String crmMedico, String nome, String especializacao, String telefone){
        this.crmMedico = crmMedico;
        this.nome = nome;
        this.especializacao = especializacao;
        this.telefone = telefone;
    }

    public String getCrmMedico() {
        return crmMedico;
    }

    public String getNome() {
        return nome;
    }

    public String getEspecializacao() {
        return especializacao;
    }

    public String getTelefone() {
        return telefone;
    }

    @Override 
    public String toString(){
        return "Medico {crmMedico = "+crmMedico+
                "; nome = "+nome+
                "; especializacao = "+especializacao+
                "; telefone = "+telefone+";}";
    }
}
