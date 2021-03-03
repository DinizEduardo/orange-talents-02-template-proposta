package br.com.zup.proposta.propostas;

import br.com.zup.proposta.compartilhado.CPForCNPJ;
import br.com.zup.proposta.compartilhado.UniqueValue;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class PropostaRequest {

    @CPForCNPJ
    @UniqueValue(fieldName = "documento", domainClass = Proposta.class)
    private String documento;

    @NotBlank
    private String nome;

    @Email(message = "E-mail invalido")
    @NotBlank
    private String email;

    @NotBlank(message = "Endereço invalido")
    private String endereco;

    @NotNull(message = "Salario invalido")
    @Positive(message = "O salario precisa ser um valor positivo")
    private double salario;

    public PropostaRequest(String documento, @NotBlank String nome, @Email(message = "E-mail invalido") @NotBlank String email, @NotBlank(message = "Endereço invalido") String endereco, @NotNull(message = "Salario invalido") @Positive(message = "O salario precisa ser um valor positivo") double salario) {
        this.documento = documento;
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.salario = salario;
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

    public String getNome() {
        return nome;
    }

    public Proposta toModel() {

        return new Proposta(documento, nome, email, endereco, salario);

    }
}
