package br.com.zup.proposta.cartoes;

public class CartaoRequestRouter {

    private String documento;

    private String nome;

    private Long idProposta;

    @Deprecated
    public CartaoRequestRouter() {
    }

    public CartaoRequestRouter(String documento, String nome, Long idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdProposta() {
        return idProposta;
    }
}
