import java.util.List;
import model.Disciplina;
import model.Horario;
import model.Professor;
import repository.Repositorio;
import service.GradeManager;

public class Main {
    public static void main(String[] args){
        System.out.println("Bem-vindo ao sistema de gerenciamento de grade de professores e disciplinas!");
        System.out.println("Iniciando o sistema...");

        //inicializando o repositório e carregando os dados
        Repositorio repositorio = new Repositorio();
        List<Professor> professores = repositorio.carregarProfessores();
        List<Disciplina> disciplinas = repositorio.carregarDisciplinas();

        GradeManager gradeManager = new GradeManager();

        Horario horario1 = new Horario(Horario.DiaSemana.SEGUNDA, Horario.Turno.NOITE);
        Horario horario2 = new Horario(Horario.DiaSemana.SEGUNDA, Horario.Turno.NOITE);
        Horario horario3 = new Horario(Horario.DiaSemana.SEGUNDA, Horario.Turno.TARDE);
        Horario horario4 = new Horario(Horario.DiaSemana.TERCA, Horario.Turno.NOITE);
        Horario horario5 = new Horario(Horario.DiaSemana.TERCA, Horario.Turno.NOITE);
        Horario horario6 = new Horario(Horario.DiaSemana.TERCA, Horario.Turno.NOITE);
        Horario horario7 = new Horario(Horario.DiaSemana.QUARTA, Horario.Turno.NOITE);
        Horario horario8 = new Horario(Horario.DiaSemana.QUINTA, Horario.Turno.NOITE);
        Horario horario9 = new Horario(Horario.DiaSemana.QUINTA, Horario.Turno.NOITE);
        Horario horario10 = new Horario(Horario.DiaSemana.SEXTA, Horario.Turno.NOITE);
        Horario horario11 = new Horario(Horario.DiaSemana.SEXTA, Horario.Turno.TARDE);

        System.out.println("\nTentando alocar aulas...\n");
        gradeManager.tentarAlocar(professores.get(0), disciplinas.get(0), horario1);
        gradeManager.tentarAlocar(professores.get(1), disciplinas.get(1), horario2);
        gradeManager.tentarAlocar(professores.get(2), disciplinas.get(2), horario3);
        gradeManager.tentarAlocar(professores.get(3), disciplinas.get(3), horario4);
        gradeManager.tentarAlocar(professores.get(4), disciplinas.get(4), horario5);

        System.out.println("\nTentando alocar aulas com conflitos...\n");
        gradeManager.tentarAlocar(professores.get(0), disciplinas.get(4), horario5); //conflito de area
        gradeManager.tentarAlocar(professores.get(1), disciplinas.get(5), horario6); //conflito de area
        
        System.out.println("\nTentando alocar aulas com conflitos de horário...\n");
        gradeManager.tentarAlocar(professores.get(0), disciplinas.get(6), horario1); //conflito de horario
        gradeManager.tentarAlocar(professores.get(1), disciplinas.get(7), horario2); //conflito de horario

        gradeManager.imprimirGrade();
    }
    
}
