package br.com.zup.proposta.propostas;

import br.com.zup.proposta.compartilhado.CPForCNPJ;

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
    private String email;

    @NotBlank
    private String endereco;

    @Positive
    private Double salario;

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

    public Proposta(String documento, @NotBlank String email, @NotBlank String endereco, @Positive Double salario) {
        this.documento = documento;
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
}
