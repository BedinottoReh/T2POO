package strategy;

import exception.ConflitoHorario;
import model.Disciplina;
import model.Horario;
import model.Professor;
import service.GradeManager;

public class ValidadorChoqueHorario implements RegraValidacao {

    @Override
    public void validar(Professor professor, Disciplina disciplina, Horario horario, GradeManager gradeManager) {

        boolean conflito = gradeManager.getGradeAlocada()

                .stream()

                .anyMatch(alocacao -> alocacao.getProfessor().equals(professor) && alocacao.getHorario().equals(horario));

        if (conflito) {

            throw new ConflitoHorario("O professor " + professor.getNome() + " já possui aula neste horário.");
        }

    }

}