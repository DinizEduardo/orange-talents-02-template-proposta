package br.com.zup.proposta.bloqueios;

import br.com.zup.proposta.cartoes.Cartao;
import br.com.zup.proposta.cartoes.CartaoResponseRouter;
import br.com.zup.proposta.cartoes.CartaoRouter;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/bloqueios")
public class BloqueioController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private CartaoRouter cartaoRouter;


    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity<?> bloqueia(@PathVariable Long id,
                                           HttpServletRequest requestInfos,
                                           @RequestHeader("user-agent") String agent) {

        Cartao cartao = Optional.ofNullable(manager.find(Cartao.class, id))
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Id de cartão não encontrado"
                ));

        try {
            cartaoRouter.bloqueio(cartao.getNumeroCartao(), new BloqueioRequest("Proposta"));

            CartaoResponseRouter respostaCartao = cartaoRouter.buscaCartaoPorId(cartao.getNumeroCartao());

            Bloqueio bloqueio = respostaCartao.getUltimoBloqueio().toModel();

            bloqueio.setInformacoesDeRequest(requestInfos.getRemoteAddr(), agent, cartao);
            cartao.addBloqueio(bloqueio);

            manager.merge(cartao);
        } catch (FeignException f) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok().build();
    }

}
