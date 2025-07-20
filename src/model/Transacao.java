package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transacao {
    private double valor;
    private TipoTransacao tipoTransacao;
    private LocalDateTime dataHora;

    public Transacao(TipoTransacao tipoTransacao, double valor){
        this.tipoTransacao = tipoTransacao;
        this.valor = valor;
        this.dataHora = LocalDateTime.now();
    }

    public double getValor() {
        return valor;
    }

    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String formatar(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format("[%s] %s de R$%.2f",
                getDataHora().format(formatter),getTipoTransacao(), getValor());
    }
}
