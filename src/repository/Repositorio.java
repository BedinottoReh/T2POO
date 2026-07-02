package repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Professor;
import model.Disciplina;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Repositorio{
    private final Gson gson;
    private final String CAMINHO_PROFESSORES = "professores.json";
    private final String CAMINHO_DISCIPLINAS = "disciplinas.json";

    public Repositorio() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    //Professores
    public List<Professor> carregarProfessores(){
        File arquivo = new File(CAMINHO_PROFESSORES);
        if(!arquivo.exists()){
            return gerarProfessoresPadrao();
        }

        try (Reader reader = new FileReader(arquivo)){
            Type tipoLista = new TypeToken<ArrayList<Professor>>(){}.getType();
            List<Professor> lista = gson.fromJson(reader, tipoLista);
            return lista != null ? lista : new ArrayList<>();
        } catch (IOException e ){
            System.err.println("Erro ao carregar professores do JSON");
            return new ArrayList<>();
        }
    }

    public void alvarProfessores(List<Professor> professores){
        try (Writer writer = new FileWriter(CAMINHO_PROFESSORES)){
            gson.toJson(professores, writer);
        } catch (IOException e){
            throw new RuntimeException("Erro ao salvar professores no arquivo");
        }
    }

    //Disciplinas
    public List<Disciplina> carregarDisciplinas(){
        File arquivo = new File(CAMINHO_DISCIPLINAS);
        if(!arquivo.exists()){
            return gerarDisciplinasPadrao();
        }
        try (Reader reader = new FileReader(arquivo)){
            Type tipoLista = new TypeToken<ArrayList<Disciplina>>(){}.getType();
            List<Disciplina> lista = gson.fromJson(reader, tipoLista);
            return lista != null ? lista : new ArrayList<>();
        } catch (IOException e){
            System.err.println("Erro ao carregar disciplinas do JSON");
            return new ArrayList<>();
        }
    }

    public void salvarDisciplinas(List<Disciplina> disciplinas){
        try (Writer writer = new FileWriter(CAMINHO_DISCIPLINAS)){
            gson.toJson(disciplinas, writer);
        } catch (IOException e){
            throw new RuntimeException("Erro ao salvar disciplinas no arquivo");
        }
    }

    //Dados de exemplo, caso os arquivos não existam
    private List<Professor> gerarProfessoresPadrao() {
        List<Professor> professores = new ArrayList<>();
        professores.add(new Professor("Prof. Celso Nobre", Arrays.asList("Matemática"))); // Index 0
        professores.add(new Professor("Prof. Claudio Schepke", Arrays.asList("Fundamentos da Computação"))); // Index 1
        professores.add(new Professor("Prof. Silvio Ereno", Arrays.asList("Fundamentos da Computação", "Tecnologias da Computação"))); // Index 2
        professores.add(new Professor("Prof. Natalia Ferrão", Arrays.asList("Matemática"))); // Index 3
        professores.add(new Professor("Prof. Marcelo Thielo", Arrays.asList("Tecnologias da Computação"))); // Index 4
        professores.add(new Professor("Prof. Aline de Mello", Arrays.asList("Fundamentos da Computação"))); // Index 5
        professores.add(new Professor("Prof. Amanda Meincke", Arrays.asList("Contexto Social e Profissional", "Fundamentos da Computação"))); // Index 6
        professores.add(new Professor("Prof. Jean Felipe Cheiran", Arrays.asList("Fundamentos da Computação", "Tecnologias da Computação"))); // Index 7
        return professores;
    }

    private List<Disciplina> gerarDisciplinasPadrao() {
        List<Disciplina> disciplinas = new ArrayList<>();
        disciplinas.add(new Disciplina("Al0013", "Circuitos Digitais", "Fundamentos da Computação", 60)); // Index 0
        disciplinas.add(new Disciplina("AL0494", "Introdução a Computação", "Fundamentos da Computação", 60)); // Index 1
        disciplinas.add(new Disciplina("AL0324", "Lógica Matemática", "Matemática", 60)); // Index 2
        disciplinas.add(new Disciplina("AL0493", "Algoritmos e Programação", "Fundamentos da Computação", 60)); // Index 3
        disciplinas.add(new Disciplina("AL0495", "Fundamentos da Matemática", "Matemática", 60)); // Index 4
        disciplinas.add(new Disciplina("AL0497", "Cálculo para Computação II", "Matemática", 60)); // Index 5
        disciplinas.add(new Disciplina("AL0508", "Arquitetura e Organização II", "Fundamentos da Computação", 60)); // Index 6
        disciplinas.add(new Disciplina("AL0050", "Programação Orientada a Objetos", "Fundamentos da Computação", 60)); // Index 7
        return disciplinas;
    }
}