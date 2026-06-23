package model;

public class Disciplina {
    private String codigo;
    private String nome;
    private String area; //area da disciplina

    public Disciplina (String codigo, String nome, String area) { //inicializar a disciplina
        this.codigo = codigo;
        this.nome = nome;
        this.area = area;
    }

    public String getNome() { //get para leitura de dados privados
        return nome;
    }
    public String getArea() { //get para leitura de dados privados
        return area;
    }

    @Override //retorna apenas o nome quando imprimir a disciplina
    public String toString(){
        return nome;
    }
    
}
