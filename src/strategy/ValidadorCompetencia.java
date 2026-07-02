package strategy;

import exception.CompetenciaInvalida;
import model.Disciplina;
import model.Horario;
import model.Professor;
import service.GradeManager;

public class ValidadorCompetencia implements RegraValidacao {

    @Override
    public void validar(
            Professor professor,
            Disciplina disciplina,
            Horario horario,
            GradeManager gradeManager) {

        if (!professor.getAreasConhecimento()
                .contains(disciplina.getArea())) {

            throw new CompetenciaInvalida("O professor " + professor.getNome() + " não possui competência para ministrar " + disciplina.getNome());
        }
        
    }

}