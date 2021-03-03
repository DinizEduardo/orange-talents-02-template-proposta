package br.com.zup.proposta.status;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "status", url = "http://localhost:9999/api/solicitacao")
public interface StatusRouter {
    @PostMapping
    StatusResponse status(StatusRequest form);
}
