package br.com.zup.proposta.cartoes;

import br.com.zup.proposta.cartoes.avisos.Aviso;
import br.com.zup.proposta.cartoes.bloqueios.Bloqueio;
import br.com.zup.proposta.cartoes.carteiras.Carteira;
import br.com.zup.proposta.cartoes.parcelas.Parcela;
import br.com.zup.proposta.cartoes.renegociacaos.Renegociacao;
import br.com.zup.proposta.cartoes.vencimentos.Vencimento;
import br.com.zup.proposta.cartoes.vencimentos.VencimentoResponse;
import br.com.zup.proposta.propostas.Proposta;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class CartaoResponse {
    private String id; // numero cartão

    private String idProposta;

    private String titular;

    private LocalDateTime emitidoEm;

    private Double limite;

    private List<Bloqueio> bloqueios;

    private List<Aviso> avisos;

    private List<Carteira> carteiras;

    private List<Parcela> parcelas;

    private Renegociacao renegociacao;

    private VencimentoResponse vencimento;

    @Override
    public String toString() {
        return "CartaoResponse{" +
                "id='" + id + '\'' +
                ", idProposta='" + idProposta + '\'' +
                ", titular='" + titular + '\'' +
                ", emitidoEm=" + emitidoEm +
                ", limite=" + limite +
                ", bloqueios=" + bloqueios +
                ", avisos=" + avisos +
                ", carteiras=" + carteiras +
                ", parcelas=" + parcelas +
                ", renegociacao=" + renegociacao +
                ", vencimento=" + vencimento +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getIdProposta() {
        return idProposta;
    }

    public String getTitular() {
        return titular;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
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

    public VencimentoResponse getVencimento() {
        return vencimento;
    }

    public Cartao toModel(Proposta proposta) {
        return new Cartao(id, emitidoEm, proposta, limite,
                bloqueios, avisos, carteiras, parcelas,
                renegociacao, vencimento.toModel());
    }
}
