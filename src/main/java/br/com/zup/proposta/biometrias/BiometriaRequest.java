package br.com.zup.proposta.biometrias;

import br.com.zup.proposta.cartoes.Cartao;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Optional;

public class BiometriaRequest {

    @Size(max = 1024)
    @NotBlank
    private String digital;

    @Deprecated
    public BiometriaRequest() {
    }

    public BiometriaRequest(String digital) {
        this.digital = digital;
    }

    public String getDigital() {
        return digital;
    }

    public Biometria toModel(Long id, EntityManager manager) {

        Cartao cartao = Optional.ofNullable(manager.find(Cartao.class, id))
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Id de cartão não encontrado"
                ));

        return new Biometria(digital, cartao);

    }
}
