package br.unicentro.appconsultas.model;

public class Consulta {
    private String cpfPaciente;
    private String crmMedico;
    private String data;

    public Consulta(String cpfPaciente, String crmMedico, String data){
        this.cpfPaciente = cpfPaciente;
        this.crmMedico = crmMedico;
        this.data = data;
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

    @Override 
    public String toString(){
        return "Consulta {cpfPaciente = "+cpfPaciente+"; crmMedico = "+crmMedico+"; data = "+data+";}";
    }
}
