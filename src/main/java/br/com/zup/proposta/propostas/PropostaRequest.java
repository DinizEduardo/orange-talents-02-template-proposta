package br.com.zup.proposta.propostas;

import br.com.zup.proposta.compartilhado.CPForCNPJ;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class PropostaRequest {

    @CPForCNPJ
    private String documento;

    @Email(message = "E-mail invalido")
    private String email;

    @NotBlank(message = "Endereço invalido")
    private String endereco;

    @NotNull(message = "Salario invalido")
    @Positive(message = "O salario precisa ser um valor positivo")
    private double salario;

    public PropostaRequest(String documento,
                           @Email(message = "E-mail invalido") String email,
                           @NotBlank(message = "Endereço invalido") String endereco,
                           @NotNull(message = "Salario invalido") @Positive(message = "O salario precisa ser um valor positivo") double salario) {
        this.documento = documento;
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

    public Proposta toModel() {

        return new Proposta(documento.replaceAll("[^\\d]", ""), email, endereco, salario);

    }
}
