package strategy;

import model.Disciplina;
import model.Horario;
import model.Professor;
import service.GradeManager;

public class ValidadorCargaHorariaDisciplina implements RegraValidacao {

    @Override
    public void validar(Professor professor, Disciplina disciplina, Horario horario, GradeManager gradeManager) {
        // Conta quantos períodos essa disciplina JÁ OCUPA na grade atualmente
        long periodosAlocados = gradeManager.getGradeAlocada().stream()
                .filter(alocacao -> alocacao.getDisciplina().getCodigo().equals(disciplina.getCodigo()))
                .count();

        // Determina o limite máximo de períodos (30h = 1 período, 60h = 2, 90h = 3)
        int limitePeriodos = disciplina.getCargaHoraria() / 30;

        // Se tentar alocar além do limite estabelecido, barra o match
        if (periodosAlocados >= limitePeriodos) {
            throw new IllegalArgumentException("A disciplina '" + disciplina.getNome() + 
                "' tem " + disciplina.getCargaHoraria() + "h e já atingiu o limite máximo de " + 
                limitePeriodos + " período(s) na semana.");
        }
    }
}