/*Representa a entidade Disciplina */

package model;

public class Disciplina {
    private final String codigo;
    private final String nome;
    private final String area; //area da disciplina
    private final int cargaHoraria; //carga horária da disciplina

    public Disciplina (String codigo, String nome, String area, int cargaHoraria) {
        if(codigo == null || codigo.trim().isEmpty()){
            throw new IllegalArgumentException("Código da disciplina não pode ser nulo ou vazio.");
        }
        if(nome == null || nome.trim().isEmpty()){
            throw new IllegalArgumentException("Nome da disciplina não pode ser nulo ou vazio.");
        }
        if(area == null || area.trim().isEmpty()){
            throw new IllegalArgumentException("Área da disciplina não pode ser nula ou vazia.");
        }
        if(cargaHoraria <= 0){
            throw new IllegalArgumentException("Carga horária da disciplina deve ser um valor positivo.");
        }
        this.codigo = codigo;
        this.nome = nome;
        this.area = area;
        this.cargaHoraria = cargaHoraria;
    }
    public String getCodigo(){return codigo;}
    public String getNome(){return nome;}
    public String getArea(){return area;}
    public int getCargaHoraria(){return cargaHoraria;}

    @Override
    public String toString() {return nome + "[" + codigo + "] - " + area + " - " + cargaHoraria + "h";}
}
