package br.com.zup.proposta.bloqueios;

public class BloqueioRequest {

    private String sistemaResponsavel;

    public BloqueioRequest(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    @Deprecated
    public BloqueioRequest() {
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
