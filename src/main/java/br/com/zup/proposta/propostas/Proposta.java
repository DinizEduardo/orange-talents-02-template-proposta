package br.com.zup.proposta.propostas;

import br.com.zup.proposta.compartilhado.CPForCNPJ;
import br.com.zup.proposta.status.StatusEnum;
import br.com.zup.proposta.status.StatusRequest;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "propostas")
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CPForCNPJ
    private String documento;

    @NotBlank
    private String nome;

    @NotBlank
    private String email;

    @NotBlank
    private String endereco;

    @Positive
    private Double salario;

    @Enumerated(EnumType.STRING)
    private PropostaStatusEnum status;

    @Override
    public String toString() {
        return "Proposta{" +
                "id=" + id +
                ", documento='" + documento + '\'' +
                ", email='" + email + '\'' +
                ", endereco='" + endereco + '\'' +
                ", salario=" + salario +
                '}';
    }

    @Deprecated
    public Proposta() {
    }

    public Proposta(String documento, @NotBlank String nome, @NotBlank String email, @NotBlank String endereco, @Positive Double salario) {
        this.documento = documento;
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.salario = salario;
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

    public Double getSalario() {
        return salario;
    }

    public String getNome() {
        return nome;
    }

    public PropostaStatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        if(status == StatusEnum.COM_RESTRICAO)
            this.status = PropostaStatusEnum.NAO_ELEGIVEL;
        else if(status == StatusEnum.SEM_RESTRICAO)
            this.status = PropostaStatusEnum.ELEGIVEL;
    }

    public StatusRequest toStatus() {
        return new StatusRequest(nome, documento, id);
    }
}
