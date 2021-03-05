package br.com.zup.proposta.biometrias;

import br.com.zup.proposta.cartoes.Cartao;

import javax.persistence.*;

@Entity
@Table(name = "biometrias")
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1024)
    private String digital;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Biometria() {
    }

    public Biometria(String digital, Cartao cartao) {
        this.digital = digital;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public String getDigital() {
        return digital;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
