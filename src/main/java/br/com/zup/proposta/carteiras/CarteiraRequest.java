package br.com.zup.proposta.carteiras;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarteiraRequest {

    @NotBlank
    @Email
    private String email;

    @NotNull
    private TipoCarteiraEnum carteira;

    public String getEmail() {
        return email;
    }

    public TipoCarteiraEnum getCarteira() {
        return carteira;
    }
}
