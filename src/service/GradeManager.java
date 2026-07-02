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
import strategy.ValidadorCargaHorariaDisciplina;
import strategy.ValidadorChoqueHorario;
import strategy.ValidadorCompetencia;

public class GradeManager {

    private List<Alocacao> gradeAlocada;
    private final List<RegraValidacao> regras;

    // Construtor Único e unificado
    public GradeManager() {
        this.gradeAlocada = new ArrayList<>(); // Inicializa a lista de alocações
        this.regras = new ArrayList<>();
        this.regras.add(new ValidadorChoqueHorario());
        this.regras.add(new ValidadorCompetencia());
        this.regras.add(new ValidadorCargaHorariaDisciplina()); // Adiciona a regra de carga horária
    }

    // Método tentarAlocar Único com a lógica de validação por Strategy da Sarah
    public void tentarAlocar(Professor professor, Disciplina disciplina, Horario horario) {
        // Executa cada regra de validação (pode lançar exceções capturadas pela GUI)
        for (RegraValidacao regra : regras) { 
            regra.validar(professor, disciplina, horario, this);
        }

        // Se passar por todas as regras, cria e adiciona a alocação
        Alocacao novaAlocacao = new Alocacao(professor, disciplina, horario);
        this.gradeAlocada.add(novaAlocacao);

        System.out.println("Alocação concluída para " + professor.getNome() + " -> " + disciplina.getNome());
    }

    public void imprimirGrade() {
        System.out.println("GRADE HORÁRIO ATUAL");
        if (gradeAlocada.isEmpty()) {
            System.out.println("Nenhuma alocação realizada.");
        } else {
            for (Alocacao alocacao : gradeAlocada) {
                System.out.println("• " + alocacao.getHorario() + " | " + 
                                   alocacao.getProfessor().getNome() + " -> " + 
                                   alocacao.getDisciplina().getNome());
            }
        }
    }

    // Seu Getter necessário para a interface MainFrame redesenhar a tabela
    public List<Alocacao> getGradeAlocada() {
        return this.gradeAlocada;
    }
    
    // Método com Stream que a Sarah usa nos validadores dela
    public List<Alocacao> buscarAlocacoesProfessor(Professor professor) {
        return gradeAlocada.stream()
                .filter(alocacao -> alocacao.getProfessor().equals(professor))
                .collect(Collectors.toList());
    }
}