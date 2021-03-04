package br.com.zup.proposta.cartoes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "cartoes", url = "http://localhost:8888/api/cartoes")
public interface CartaoRouter {

    @PostMapping
    CartaoResponse criaCartao(CartaoRequest form);

}
