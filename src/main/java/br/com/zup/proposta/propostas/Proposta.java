package br.com.zup.proposta.propostas;

import br.com.zup.proposta.cartoes.Cartao;
import br.com.zup.proposta.cartoes.CartaoRequestRouter;
import br.com.zup.proposta.compartilhado.CPForCNPJ;
import br.com.zup.proposta.status.StatusEnum;
import br.com.zup.proposta.status.StatusRequest;
import com.google.common.hash.Hashing;
import org.springframework.security.crypto.encrypt.Encryptors;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Entity
@Table(name = "propostas")
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @OneToOne(cascade = CascadeType.MERGE)
    private Cartao cartao;

    @Column(length = 64, unique = true)
    private String documentoHashed;

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
        this.documento = Encryptors.text("abcabc", "cbacba").encrypt(documento);
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.salario = salario;
        this.documentoHashed = Hashing.sha256()
                .hashString(documento, StandardCharsets.UTF_8)
                .toString();
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

    public Cartao getCartao() {
        return cartao;
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
        return new StatusRequest(documento, nome, id);
    }

    public CartaoRequestRouter toCartaoRequest() {

        return new CartaoRequestRouter(documento, nome, id);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Proposta)) return false;
        Proposta proposta = (Proposta) o;
        return Objects.equals(getId(), proposta.getId()) && Objects.equals(getDocumento(), proposta.getDocumento()) && Objects.equals(getNome(), proposta.getNome()) && Objects.equals(getEmail(), proposta.getEmail()) && Objects.equals(getEndereco(), proposta.getEndereco()) && Objects.equals(getSalario(), proposta.getSalario()) && getStatus() == proposta.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDocumento(), getNome(), getEmail(), getEndereco(), getSalario(), getStatus());
    }

    public void toCartaoResponse(Cartao cartao) {

        this.cartao = cartao;

    }
}
