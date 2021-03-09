package br.com.zup.proposta.cartoes;

import br.com.zup.proposta.avisos.Aviso;
import br.com.zup.proposta.bloqueios.Bloqueio;
import br.com.zup.proposta.carteiras.Carteira;
import br.com.zup.proposta.cartoes.parcelas.Parcela;
import br.com.zup.proposta.cartoes.renegociacaos.Renegociacao;
import br.com.zup.proposta.cartoes.vencimentos.Vencimento;
import br.com.zup.proposta.propostas.Proposta;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "cartoes")
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroCartao;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyy-MM-dd@HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "America/Sao_Paulo")
    private LocalDateTime emitidoEm;

    @OneToOne
    private Proposta proposta;

    private Double limite;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private List<Bloqueio> bloqueios;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private List<Aviso> avisos;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private List<Carteira> carteiras;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private List<Parcela> parcelas;

    @OneToOne(cascade = CascadeType.MERGE)
    private Renegociacao renegociacao;

    @OneToOne(cascade = CascadeType.MERGE)
    private Vencimento vencimento;

    @Deprecated
    public Cartao() {
    }

    public Cartao(String numeroCartao,
                  LocalDateTime emitidoEm,
                  Proposta proposta,
                  Double limite,
                  List<Bloqueio> bloqueios,
                  List<Aviso> avisos,
                  List<Carteira> carteiras,
                  List<Parcela> parcelas,
                  Renegociacao renegociacao,
                  Vencimento vencimento) {
        this.numeroCartao = numeroCartao;
        this.emitidoEm = emitidoEm;
        this.proposta = proposta;
        this.limite = limite;
        this.bloqueios = bloqueios;
        this.avisos = avisos;
        this.carteiras = carteiras;
        this.parcelas = parcelas;
        this.renegociacao = renegociacao;
        this.vencimento = vencimento;
    }

    public static Cartao findByNumeroCartao(String numeroCartao, EntityManager manager) {
        Query query = manager.createQuery("SELECT c FROM Cartao c WHERE numeroCartao = :numero");

        query.setParameter("numero", numeroCartao);

        return (Cartao) query.getSingleResult();
    }

    public Long getId() {
        return id;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public Proposta getProposta() {
        return proposta;
    }

    public Double getLimite() {
        return limite;
    }

    public List<Bloqueio> getBloqueios() {
        return bloqueios;
    }

    public List<Aviso> getAvisos() {
        return avisos;
    }

    public List<Carteira> getCarteiras() {
        return carteiras;
    }

    public List<Parcela> getParcelas() {
        return parcelas;
    }

    public Renegociacao getRenegociacao() {
        return renegociacao;
    }

    public Vencimento getVencimento() {
        return vencimento;
    }

    @Override
    public String toString() {
        return "Cartao{" +
                "id=" + id +
                ", numeroCartao='" + numeroCartao + '\'' +
                ", emitidoEm=" + emitidoEm +
                ", proposta=" + proposta +
                ", limite=" + limite +
                ", bloqueios=" + bloqueios +
                ", avisos=" + avisos +
                ", carteiras=" + carteiras +
                ", parcelas=" + parcelas +
                ", renegociacao=" + renegociacao +
                ", vencimento=" + vencimento.toString() +
                '}';
    }

    public void addBloqueio(Bloqueio bloqueio) {
        this.bloqueios.add(bloqueio);
    }

    public void addCarteira(Carteira carteira) {
        this.carteiras.add(carteira);
    }
}
