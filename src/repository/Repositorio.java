//classe para simular um banco de dados
package repository;

import model.Disciplina;
import model.Professor;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

//lista de professores e areas de conhecimento
public class Repositorio {
    public List<Professor> carregarProfessores(){
        List<Professor> professores = new ArrayList<>();
        professores.add(new Professor("Prof. Celso Nobre", Arrays.asList("Matemática")));
        professores.add(new Professor("Prof. Claudio Schepke", Arrays.asList("Fundamentos da Computação")));
        professores.add(new Professor("Prof. Silvio Ereno", Arrays.asList("Fundamentos da Computação", "Tecnologias da Computação")));
        professores.add(new Professor("Prof. Natalia Ferrão", Arrays.asList( "Matemática")));
        professores.add(new Professor("Prof. Marcelo Thielo", Arrays.asList( "Tecnologias da Computação")));
        professores.add(new Professor("Prof. Aline de Mello", Arrays.asList( "Fundamentos da Computação")));
        professores.add(new Professor("Prof. Amanda Meincke", Arrays.asList( "Contexto Social e Profissional", "Fundamentos da Computação")));
        professores.add(new Professor("Prof. Jean Felipe Cheiran", Arrays.asList( "Fundamentos da Computação", "Tecnologias da Computação")));

        return professores;
    }

    //lista de disciplinas com codigo, nome e area
    public List<Disciplina> carregarDisciplinas(){
        List<Disciplina> disciplinas = new ArrayList<>();
        disciplinas.add(new Disciplina("Al0013", "Circuitos Digitais", "Fundamentos da Computação"));
        disciplinas.add(new Disciplina("Al0494", "Introdução a Computação","Fundamentos da Computação"));
        disciplinas.add(new Disciplina("AL0324", "Lógica Matemática","Matemática"));
        disciplinas.add(new Disciplina("AL0493", "Algoritmos e Programação para Computação","Fundamentos da Computação"));
        disciplinas.add(new Disciplina("AL0495", "Fundamentos da Matemática para Computação","Matemática"));
        disciplinas.add(new Disciplina("AL0497", "Cálculo para Computação II", "Matemática"));
        disciplinas.add(new Disciplina("AL0508", "Arquitetura e Organização de Computadores II", "Fundamentos da Computação"));
        disciplinas.add(new Disciplina("AL0050", "Programação Orientada a Objetos", "Fundamentos da Computação"));
        disciplinas.add(new Disciplina("AL0507", "Estrutura de Dados II", "Fundamentos da Computação"));
        disciplinas.add(new Disciplina("AL0009", "Álgebra Linear", "Matemática"));


        return disciplinas;
    }
    
}
