package model;

import java.util.ArrayList;
import java.util.List;

public class Conta {

    private int numero;
    private String titular;
    private double saldo;
    private List<Transacao> transacoes = new ArrayList<>();


    public Conta(String titular){
        this.titular = titular;
        this.saldo = 0.0;
        this.numero = (int) (Math.random() * 10000); //Simular número da conta
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public List<Transacao> getTransacoes(){
        return transacoes;
    }

    public void depositar(double valor) throws ValorInvalidoException{
        if(valor <= 0) throw new ValorInvalidoException("depósito");
        this.saldo = getSaldo() + valor;
        transacoes.add(new Transacao(TipoTransacao.DEPOSITO, valor));
    }

    public void sacar(double valor) throws ValorInvalidoException, SaldoInsuficienteException{
        if(valor <= 0) throw new ValorInvalidoException("saque");
        if(getSaldo() < valor) throw new SaldoInsuficienteException("saque");
        this.saldo = getSaldo() - valor;
        transacoes.add(new Transacao(TipoTransacao.SAQUE, valor));
    }

    @Override
    public String toString() {
        return String.format("\nConta n° %d | Titular: %s | Saldo: R$%.2f",
                getNumero(), getTitular(), getSaldo());
    }
}
