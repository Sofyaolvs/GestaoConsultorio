package Excecoes;
public class NaoCadastradoException extends RuntimeException{
    public NaoCadastradoException(String mensagem){
        super(mensagem);
    }
}
