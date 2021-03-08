package br.com.zup.proposta.status;

public class StatusResponse {

    private Long idProposta;

    private String documento;

    private String nome;

    private StatusEnum resultadoSolicitacao;

    @Override
    public String toString() {
        return "StatusResponse{" +
                "idProposta=" + idProposta +
                ", documento='" + documento + '\'' +
                ", nome='" + nome + '\'' +
                ", resultadoSolicitacao=" + resultadoSolicitacao +
                '}';
    }

    public Long getIdProposta() {
        return idProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public StatusEnum getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }
}
