package br.com.zup.proposta.propostas;

import org.springframework.security.crypto.encrypt.Encryptors;

public class PropostaResponse {

    private Long id;

    private String nome;

    private String documento;

    private String email;

    private String endereco;

    private double salario;

    private PropostaStatusEnum status;

    @Deprecated
    public PropostaResponse() {
    }

    public PropostaResponse(Proposta proposta) {
        this.id = proposta.getId();
        this.email = proposta.getEmail();
        this.documento = Encryptors.text("abcabc", "cbacba").decrypt(proposta.getDocumento());
        this.endereco = proposta.getEndereco();
        this.salario = proposta.getSalario();
        this.nome = proposta.getNome();
        this.status = proposta.getStatus();
    }

    public String getNome() {
        return nome;
    }

    public PropostaStatusEnum getStatus() {
        return status;
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
