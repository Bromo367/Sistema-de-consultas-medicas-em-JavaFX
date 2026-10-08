package br.unicentro.appestudantes.model;

public class Receita {
    private String cpfPaciente;
    private String crmMedico;
    private String data;
    private int medicamentoId;
    private double dose;
    private String unidadeMedida;
    private String intervalo;

    public Receita(String cpfPaciente, String crmMedico, String data, int medicamentoId, double dose, String unidadeMedida, String intervalo){
        this.cpfPaciente = cpfPaciente;
        this.crmMedico = crmMedico;
        this.data = data;
        this.medicamentoId = medicamentoId;
        this.dose = dose;
        this.unidadeMedida = unidadeMedida;
        this.intervalo = intervalo;
    }

    public String getCpfPaciente() {
        return cpfPaciente;
    }

    public String getCrmMedico() {
        return crmMedico;
    }

    public String getData() {
        return data;
    }

    public int getMedicamentoId() {
        return medicamentoId;
    }

    public double getDose() {
        return dose;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public String getIntervalo() {
        return intervalo;
    }

    @Override 
    public String toString(){
        return "Receita {cpfPaciente = "+cpfPaciente+
                "; crmMedico = "+crmMedico+
                "; data = "+data+
                "; medicamentoId = "+medicamentoId+
                "; dosagem: "+dose+" "+unidadeMedida+" "+intervalo+";}";
    }
}
