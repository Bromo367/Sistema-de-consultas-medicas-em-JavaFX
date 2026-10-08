package br.unicentro.appestudantes.model;

public class Paciente{
    private String cpfPaciente;
    private String nome;
    private int idade;
    private double peso;
    private double altura;
    private String telefone;

    public Paciente(String cpfPaciente, String nome, int idade, double peso, double altura, String telefone){
        this.cpfPaciente = cpfPaciente;
        this.nome = nome;
        this.idade = idade;
        this.peso = peso;
        this.altura = altura;
        this.telefone = telefone;
    }

    public String getCpfPaciente() {
        return cpfPaciente;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }
    public double getPeso() {
        return peso;
    }

    public double getAltura() {
        return altura;
    }

    public String getTelefone() {
        return telefone;
    }

    @Override 
    public String toString(){
        return "Paciente {cpfPaciente = "+cpfPaciente+
                "; nome = "+nome+
                "; idade = "+idade+
                "; peso = "+peso+
                "; altura = "+altura+
                "; telefone = "+telefone+";}";
    }
}