package model;

import java.util.Objects;

public final class Horario { //final para que ela não possa ter classes filhas
    public enum DiaSemana { //enum para representar os dias da semana e restringir os valores possíveis
        SEGUNDA, TERCA, QUARTA, QUINTA, SEXTA
    }
    public enum Turno {
        MANHA, TARDE, NOITE
    }

    private DiaSemana dia;
    private Turno turno;

    public Horario(DiaSemana dia, Turno turno) {
        this.dia = dia;
        this.turno = turno;
    }

    public DiaSemana getDia() { //get para leitura de dados privados
        return dia;
    }
    public Turno getTurno() {
        return turno;
    }

    @Override
    public boolean equals(Object o) { //equals para comparar os dois horarios diferentes
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Horario horario = (Horario) o;
        return dia == horario.dia && turno == horario.turno;
    }

    @Override
    public int hashCode() { //hashCode trabalha junto com o equals para comparar os objetos
        return Objects.hash(dia, turno);
    }

    @Override
    public String toString(){ //toString para representar o objeto como uma string
        return dia + " - " + turno;
    }
    
}
