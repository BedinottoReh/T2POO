package model;

import java.util.List;

public class Professor {
    private String nome;
    private List<String> areasConhecimento; //areas de conhecimento do professor

    public Professor(String nome, List<String> areasConhecimento) { //construtor para inicializar o professor
        this.nome = nome;
        this.areasConhecimento = areasConhecimento;
    }

    public String getNome() { //get para leitura de dados privados
        return nome;
    }

    public boolean possuiAreaConhecimento(String area) { //verifica se o professor possui a area de conhecimento
        return areasConhecimento.contains(area);
    }

    @Override
    public String toString() { //toString para representar o objeto como uma string
        return nome;
    }
}
