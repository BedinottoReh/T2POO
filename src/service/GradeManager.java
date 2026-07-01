//classe principal de lógica
package service;

import java.util.ArrayList;
import java.util.List;
import model.Alocacao;
import model.Disciplina;
import model.Horario;
import model.Professor;

public class GradeManager {

    private List<Alocacao> gradeAlocada;


    public GradeManager() {
        this.gradeAlocada = new ArrayList<>(); //inicializa a lista de alocações
    }

    public void tentarAlocar(Professor professor, Disciplina disciplina, Horario horario) {
        try {
            Alocacao novaAlocacao = new Alocacao(professor, disciplina, horario);
            this.gradeAlocada.add(novaAlocacao);
            System.out.println("Alocação concluida para "+ professor.getNome() + "->" + disciplina.getNome());

        } catch (IllegalArgumentException e){
            System.err.println("Erro ao alocar: " + e.getMessage());
        }
    }

    public void imprimirGrade(){
        System.out.println("GRADE HORÁRIO ATUAL");
        if(gradeAlocada.isEmpty()){
            System.out.println("Nenhuma alocação realizada.");
        } else {
            for (Alocacao alocacao : gradeAlocada) {
                System.out.println("• " + alocacao.getHorario() + " | " + 
                                   alocacao.getProfessor().getNome() + " -> " + 
                                   alocacao.getDisciplina().getNome());
            }
        }

    }
}

