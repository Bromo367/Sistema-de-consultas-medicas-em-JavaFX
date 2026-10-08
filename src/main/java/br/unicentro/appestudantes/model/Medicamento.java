package br.unicentro.appestudantes.model;

public class Medicamento{
    private int medicamentoId;
    private String nome;

    public Medicamento(int medicamentoId, String nome){
        this.nome=nome;
        this.medicamentoId=medicamentoId;
    }

    public String getNome() {
        return nome;
    }
    
    public int getMedicamentoId() {
        return medicamentoId;
    }

    @Override
    public String toString(){
        return "Medicamento {medicamentoId = "+medicamentoId+"; nome = "+nome+";}";
    }
}