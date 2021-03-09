package br.com.zup.proposta.avisos;

import br.com.zup.proposta.cartoes.Cartao;
import br.com.zup.proposta.cartoes.CartaoRouter;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/avisos")
public class AvisoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private CartaoRouter cartaoRouter;

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity<?> criar(@PathVariable Long id,
                                   @RequestBody @Valid AvisoRequest form,
                                   HttpServletRequest requestInfos,
                                   @RequestHeader("user-agent") String agent) {

        Cartao cartao = Optional.ofNullable(manager.find(Cartao.class, id))
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Id de cartão não encontrado"
                ));

        try {
            cartaoRouter.notificaAviso(cartao.getNumeroCartao(), form);

            Aviso aviso = form.toModel(cartao, requestInfos.getRemoteAddr(), agent);

            manager.merge(aviso);

            return ResponseEntity.ok().build();

        } catch (FeignException e) {
            return ResponseEntity.badRequest().build();
        }

    }

}
