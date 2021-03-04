package br.com.zup.proposta.cartoes.parcelas;

import br.com.zup.proposta.cartoes.Cartao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "parcelas")
public class Parcela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String idExterno;

    private int quantidade;

    private double valor;

    @ManyToOne
    private Cartao cartao;



    @Deprecated
    public Parcela() {
    }

    public Parcela(@NotBlank String idExterno, int quantidade, double valor, Cartao cartao) {
        this.idExterno = idExterno;
        this.quantidade = quantidade;
        this.valor = valor;
        this.cartao = cartao;
    }
}
