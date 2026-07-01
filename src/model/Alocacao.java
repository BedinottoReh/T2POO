/*//classe associativa, para unir professor, disciplina e horario */
package model;

public class Alocacao {
    private final Professor professor;
    private final Disciplina disciplina;
    private final Horario horario;

    public Alocacao(Professor professor, Disciplina disciplina, Horario horario) {
        if (professor == null || disciplina == null || horario == null) {
            throw new IllegalArgumentException("Professor, disciplina e horário não podem ser nulos.");
        }
        this.professor = professor;
        this.disciplina = disciplina;
        this.horario = horario;
    }

    public Professor getProfessor() {return professor;}
    public Disciplina getDisciplina() {return disciplina;}
    public Horario getHorario() {return horario;}
}
