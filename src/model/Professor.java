/*Representa a entidade Professor */

package model;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Professor {
    private final String nome;
    private final Set<String> areasConhecimento = new HashSet<>(); //conjunto de áreas de conhecimento do professor

    public Professor(String nome, List<String> areasInciais){
        if (nome == null || nome.trim().isEmpty()){
            throw new IllegalArgumentException("Nome do professor não pode ser nulo ou vazio.");
        }
        this.nome = nome;
        if (areasInciais != null) {
            this.areasConhecimento.addAll(areasInciais);
        }
    }

    public String getNome() {
        return nome;
    }

    public Set<String> getAreasConhecimento() {
        return Collections.unmodifiableSet(areasConhecimento); // Retorna uma cópia para evitar modificação externa
    }

    @Override
    public String toString(){ return nome;}
}