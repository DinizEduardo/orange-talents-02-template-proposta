package br.com.zup.proposta.cartoes;

import br.com.zup.proposta.bloqueios.BloqueioRequest;
import br.com.zup.proposta.bloqueios.BloqueioResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "cartoes", url = "${cartao.api}/api/cartoes")
public interface CartaoRouter {

    @PostMapping
    CartaoResponseRouter criaCartao(CartaoRequestRouter form);

    @PostMapping("/{id}/bloqueios")
    BloqueioResponse bloqueio(@PathVariable String id, BloqueioRequest request);

    @GetMapping("/{id}")
    CartaoResponseRouter buscaCartaoPorId(@PathVariable String id);
}
