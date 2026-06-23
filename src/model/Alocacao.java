package model;

//classe associativa, para unir professor, disciplina e horario
public class Alocacao {
    private Professor professor;
    private Disciplina disciplina;
    private Horario horario;

    public Alocacao(Professor professor, Disciplina disciplina, Horario horario) { //construtor para inicializar a alocacao
        this.professor = professor;
        this.disciplina = disciplina;
        this.horario = horario;
    }

    public Professor getProfessor() { //getters para leitura de dados privados
        return professor;
    }
    public Disciplina getDisciplina() { 
        return disciplina;
    }
    public Horario getHorario() { 
        return horario;
    }

    @Override
    public String toString() { //formatação da impressão da linha da grade
        return String.format("[%s] %s leciona %s", horario, professor.getNome(), disciplina.getNome());
    }
}
