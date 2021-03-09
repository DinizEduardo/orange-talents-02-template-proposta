package br.com.zup.proposta.avisos;

import br.com.zup.proposta.cartoes.Cartao;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "avisos")
public class Aviso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate validoAte;

    private String destino;

    @ManyToOne
    private Cartao cartao;

    private String ipCliente;

    private String userAgent;

    private LocalDateTime criadoEm = LocalDateTime.now();

    @Deprecated
    public Aviso() {
    }

    public Aviso(LocalDate validoAte, String destino, Cartao cartao) {
        this.validoAte = validoAte;
        this.destino = destino;
        this.cartao = cartao;
    }

    public Aviso(AvisoRequest avisoRequest, Cartao cartao, String requestInfos, String agent) {

            this.cartao = cartao;
            this.userAgent = agent;
            this.ipCliente = requestInfos;
            this.destino = avisoRequest.getDestino();
            this.validoAte = avisoRequest.getValidoAte();

    }

    public String getIpCliente() {
        return ipCliente;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
