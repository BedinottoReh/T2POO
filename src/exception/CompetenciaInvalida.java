package exception;

public class CompetenciaInvalida extends RuntimeException {

    // Construtor 
    public CompetenciaInvalida(String mensagem) {
        super(mensagem); //super para chamar o construtor da classe mãe
    }

}