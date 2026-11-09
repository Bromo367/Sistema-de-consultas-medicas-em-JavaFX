package br.unicentro.appconsultas.build;

import br.unicentro.appconsultas.model.Paciente;

public class PacienteBuilder{
    private String cpfPaciente;
    private String nome;
    private int idade;
    private double peso;
    private double altura;
    private String telefone;

    public PacienteBuilder cpfPaciente(String cpf){
        this.cpfPaciente = cpf;
        return this;
    }
    public PacienteBuilder nome(String nome){
        this.nome = nome;
        return this;
    }
    public PacienteBuilder idade(Integer idade){
        this.idade = idade;
        return this;
    }
    public PacienteBuilder peso(Double peso){
        this.peso = peso;
        return this;
    }
    public PacienteBuilder altura(Double altura){
        this.altura = altura;
        return this;
    }
    public PacienteBuilder telefone(String telefone){
        this.telefone = telefone;
        return this;
    }

    public Paciente build(){
        return new Paciente(cpfPaciente,nome,idade,peso,altura,telefone);
    }
}