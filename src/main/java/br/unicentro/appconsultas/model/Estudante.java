package br.unicentro.appconsultas.model;

public class Estudante {

    private int estudanteID;
    private String nome;
    private Integer idade;

    public Estudante() {
    }

    public Estudante(int estudanteID, String nome, Integer idade) {
        this.estudanteID = estudanteID;
        this.nome = nome;
        this.idade = idade;
    }

    public int getEstudanteID() {
        return estudanteID;
    }

    public void setEstudanteID(int estudanteID) {
        this.estudanteID = estudanteID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "Estudante{" +
                "estudanteID=" + estudanteID +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                '}';
    }
}

