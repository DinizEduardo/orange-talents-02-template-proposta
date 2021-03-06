package br.com.zup.proposta.carteiras;

import br.com.zup.proposta.cartoes.Cartao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "carteiras")
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String idExterno;

    private String email;

    private LocalDateTime associadaEm;

    @Enumerated(EnumType.STRING)
    private TipoCarteiraEnum emissor;

    @ManyToOne
    private Cartao cartao;

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getAssociadaEm() {
        return associadaEm;
    }

    public TipoCarteiraEnum getEmissor() {
        return emissor;
    }

    @Deprecated
    public Carteira() {
    }

    public Carteira(@NotBlank String idExterno, String email, LocalDateTime associadaEm, TipoCarteiraEnum emissor, Cartao cartao) {
        this.idExterno = idExterno;
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
        this.cartao = cartao;
    }

    public String getIdExterno() {
        return idExterno;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
