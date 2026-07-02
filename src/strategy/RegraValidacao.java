package strategy;

import model.Disciplina;
import model.Horario;
import model.Professor;
import service.GradeManager;

public interface RegraValidacao {

    void validar(
            Professor professor,
            Disciplina disciplina,
            Horario horario,
            GradeManager gradeManager);

}