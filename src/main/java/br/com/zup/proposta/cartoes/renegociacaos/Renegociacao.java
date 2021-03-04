package br.com.zup.proposta.cartoes.renegociacaos;

import br.com.zup.proposta.cartoes.Cartao;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "renegociacoes")
public class Renegociacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idExterno;

    private Integer qunatidade;

    private Double valor;

    private LocalDateTime dataDeCriacao;

    public Long getId() {
        return id;
    }

    public String getIdExterno() {
        return idExterno;
    }

    public Integer getQunatidade() {
        return qunatidade;
    }

    public Double getValor() {
        return valor;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }

    public Renegociacao(String idExterno, Integer qunatidade, Double valor, LocalDateTime dataDeCriacao) {
        this.idExterno = idExterno;
        this.qunatidade = qunatidade;
        this.valor = valor;
        this.dataDeCriacao = dataDeCriacao;
    }

    @Deprecated
    public Renegociacao() {
    }
}
