//classe principal de lógica
package service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.Alocacao;
import model.Disciplina;
import model.Horario;
import model.Professor;
import strategy.RegraValidacao;
import strategy.ValidadorChoqueHorario;
import strategy.ValidadorCompetencia;

public class GradeManager {

    private List<Alocacao> gradeAlocada;

    private final List<RegraValidacao> regras;


    public GradeManager() {
        this.gradeAlocada = new ArrayList<>(); //inicializa a lista de alocações
        this.regras = new ArrayList<>();
        this.regras.add(new ValidadorChoqueHorario());
        this.regras.add(new ValidadorCompetencia());
    }

    public void tentarAlocar(Professor professor, Disciplina disciplina, Horario horario) {

    for (RegraValidacao regra : regras) { //executa cada regra de validação
        regra.validar(professor, disciplina, horario, this);
    }

    Alocacao novaAlocacao =
            new Alocacao(professor, disciplina, horario);

    this.gradeAlocada.add(novaAlocacao);

    System.out.println("Alocação concluída para " + professor.getNome() + " -> " + disciplina.getNome());
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

    public List<Alocacao> getGradeAlocada() {
        return this.gradeAlocada;
    }
    
    public List<Alocacao> buscarAlocacoesProfessor(Professor professor) {
        return gradeAlocada.stream()
                .filter(alocacao ->
                        alocacao.getProfessor().equals(professor))
                .collect(Collectors.toList());
    }
}