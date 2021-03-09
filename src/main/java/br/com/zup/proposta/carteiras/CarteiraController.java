package br.com.zup.proposta.carteiras;

import br.com.zup.proposta.cartoes.Cartao;
import br.com.zup.proposta.cartoes.CartaoResponseRouter;
import br.com.zup.proposta.cartoes.CartaoRouter;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/carteiras/{id}")
public class CarteiraController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private CartaoRouter cartaoRouter;

    @PostMapping
    @Transactional
    public ResponseEntity<?> criar(@PathVariable Long id,
                                   @RequestBody @Valid CarteiraRequest form,
                                   UriComponentsBuilder uriBuilder) {

        Cartao cartao = Optional.ofNullable(manager.find(Cartao.class, id))
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Id de cartão não encontrado"
                ));
        try {

            cartaoRouter.criaCarteira(cartao.getNumeroCartao(), form);
            CartaoResponseRouter response = cartaoRouter.buscaCartaoPorId(cartao.getNumeroCartao());

            Carteira carteira = response.getUltimaCarteira().toModel(cartao);

            cartao.addCarteira(carteira);

            manager.merge(cartao);

            URI uri = uriBuilder.path("/carteiras/{id}").buildAndExpand(carteira.getId()).toUri();

            return ResponseEntity.created(uri).build();
        }catch (FeignException e) {
            return ResponseEntity.unprocessableEntity().build();
        }

    }

}
