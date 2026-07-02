/* Representa o horário de uma aula */

package model;

public final class Horario { //final para que ela não possa ter classes filhas
    public enum DiaSemana { //enum para representar os dias da semana e restringir os valores possíveis
        SEGUNDA, TERCA, QUARTA, QUINTA, SEXTA
    }
    public enum Turno {
        MANHA, TARDE, NOITE
    }

    private final DiaSemana diaSemana;
    private final Turno turno;

    public Horario(DiaSemana diaSemana, Turno turno) {
        if (diaSemana == null || turno == null) {
            throw new IllegalArgumentException("Dia da semana e turno não podem ser nulos.");
        }
        this.diaSemana = diaSemana;
        this.turno = turno;
    }

    public DiaSemana getDiaSemana() { //get para leitura de dados privados
        return diaSemana;
    }
    public Turno getTurno() {
        return turno;
    }

    @Override
    public boolean equals(Object o) { //equals para comparar os dois horarios diferentes
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Horario horario = (Horario) o;
        return diaSemana == horario.diaSemana && turno == horario.turno;
    }

    @Override
    public int hashCode() { //hashCode para gerar um código único para cada horario
        return diaSemana.hashCode() * 31 + turno.hashCode();
    }

    @Override
    public String toString() { //toString para representar o horario como uma string
        return diaSemana + " - " + turno;
    }
}
