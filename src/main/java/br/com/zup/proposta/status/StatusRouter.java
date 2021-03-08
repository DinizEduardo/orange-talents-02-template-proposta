package br.com.zup.proposta.status;

import feign.FeignException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "status", url = "${status.api}/api/solicitacao")
public interface StatusRouter {
    @PostMapping
    StatusResponse status(StatusRequest form);
}
