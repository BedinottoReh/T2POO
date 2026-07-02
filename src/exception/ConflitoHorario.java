package exception;

public class ConflitoHorario extends RuntimeException {

    public ConflitoHorario(String mensagem) {
        super(mensagem);
    }

}