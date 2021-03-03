package br.com.zup.proposta.propostas;

public class PropostaResponse {

    private Long id;

    private String documento;

    private String email;

    private String endereco;

    private double salario;

    @Deprecated
    public PropostaResponse() {
    }

    public PropostaResponse(Proposta proposta) {
        this.id = proposta.getId();
        this.email = proposta.getEmail();
        this.documento = proposta.getDocumento();
        this.endereco = proposta.getEndereco();
        this.salario = proposta.getSalario();
    }


    public Long getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public double getSalario() {
        return salario;
    }
}
