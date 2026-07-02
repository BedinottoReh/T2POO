package exception;

public class ConflitoHorario extends RuntimeException {

    // construtor
    public ConflitoHorario(String mensagem) {
        super(mensagem);
    }

}