/* Representa o horário de uma aula */

package model;

public final class Horario { //final para que ela não possa ter classes filhas
    public enum DiaSemana { //enum para representar os dias da semana e restringir os valores possíveis
        SEGUNDA, TERCA, QUARTA, QUINTA, SEXTA
    }
    public enum Turno {
        MANHA, TARDE, NOITE
    }
    public enum Bloco { PRIMEIRO, SEGUNDO }

    private final DiaSemana diaSemana;
    private final Turno turno;
    private Bloco bloco;

    public Horario(DiaSemana diaSemana, Turno turno, Bloco bloco) {
        if (diaSemana == null || turno == null || bloco == null) {
            throw new IllegalArgumentException("Dia da semana, turno e bloco não podem ser nulos.");
        }
        this.diaSemana = diaSemana;
        this.turno = turno;
        this.bloco = bloco;
    }

    public DiaSemana getDiaSemana() { //get para leitura de dados privados
        return diaSemana;
    }
    public Turno getTurno() {
        return turno;
    }
    public Bloco getBloco() {
        return bloco;
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
    public String toString() {
        String num = (bloco == Bloco.PRIMEIRO) ? "1º Horário" : "2º Horário";
        return diaSemana + " - " + turno + " (" + num + ")";
    }
}
