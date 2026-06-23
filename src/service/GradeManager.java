//classe principal de lógica
package service;

import model.Alocacao;
import model.Disciplina;
import model.Horario;
import model.Professor;
import java.util.List;
import java.util.ArrayList;

public class GradeManager {

    private List<Alocacao> gradeAlocada;


    public GradeManager() {
        this.gradeAlocada = new ArrayList<>(); //inicializa a lista de alocações
    }

    //método para agendar uma aula
    public boolean tentarAlocar(Professor professor, Disciplina disciplina, Horario horario) {

        //verifica se o professor possui a area de conhecimento da disciplina
        if (!professor.possuiAreaConhecimento(disciplina.getArea())) {
            System.out.println(professor.getNome() + " não possui conhecimento na área de " + disciplina.getArea());
            return false;
        }

        //verifica se o professor já está alocado em outro horário
        for (Alocacao alocacaoExistente : gradeAlocada) {
            if (alocacaoExistente.getHorario().equals(horario) && alocacaoExistente.getProfessor().getNome().equals(professor.getNome())){
                System.out.println(professor.getNome() + "já possui aula neste horário.");
                return false;
            }
            }

            //criando a alocação de aula
            gradeAlocada.add(new Alocacao(professor, disciplina, horario));
            System.out.println(disciplina.getNome() + " com " + professor.getNome() + " no horário " + horario + " alocada com sucesso.");
            return true;
        }

        //método para imprimir a grade de aulas alocadas
        public void imprimirGrade(){
            System.out.println("\n--- Grade de Aulas Alocadas ---");
            if (gradeAlocada.isEmpty()){
                System.out.println("Nenhuma aula alocada.");
            } else {
                for (Alocacao alocacao : gradeAlocada) {
                    System.out.println(alocacao);
                }
            }
            System.out.println("-------------------------------\n");
        }
    }

