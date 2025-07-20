package model;

public class ValorInvalidoException extends Exception{
    public ValorInvalidoException(String mensagem){
        super("\nvalor inválido para " + mensagem + ". Digite um valor mair que zero");
    }
}
