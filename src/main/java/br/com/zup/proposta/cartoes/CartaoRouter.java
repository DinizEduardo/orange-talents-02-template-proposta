package br.com.zup.proposta.cartoes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "cartoes", url = "${cartao.api}/api/cartoes")
public interface CartaoRouter {

    @PostMapping
    CartaoResponseRouter criaCartao(CartaoRequestRouter form);

}
