package model;

public class SaldoInsuficienteException extends Exception{
    public SaldoInsuficienteException(String mensagem){
        super("\nSaldo insuficiente para " + mensagem);
    }
}
