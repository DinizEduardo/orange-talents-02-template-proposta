package br.com.zup.proposta.cartoes;

import br.com.zup.proposta.avisos.AvisoRequest;
import br.com.zup.proposta.avisos.AvisoResponse;
import br.com.zup.proposta.bloqueios.BloqueioRequest;
import br.com.zup.proposta.bloqueios.BloqueioResponse;
import br.com.zup.proposta.carteiras.CarteiraRequest;
import br.com.zup.proposta.carteiras.CarteiraResponse;
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

    @PostMapping("/{id}/avisos")
    AvisoResponse notificaAviso(@PathVariable String id, AvisoRequest request);

    @PostMapping("/{id}/carteiras")
    CarteiraResponse criaCarteira(@PathVariable String id, CarteiraRequest request);
}
