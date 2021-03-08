package br.com.zup.proposta.propostas;

import br.com.zup.proposta.cartoes.Cartao;
import br.com.zup.proposta.cartoes.CartaoResponseRouter;
import br.com.zup.proposta.cartoes.CartaoRouter;
import br.com.zup.proposta.status.StatusEnum;
import br.com.zup.proposta.status.StatusResponse;
import br.com.zup.proposta.status.StatusRouter;
import feign.FeignException;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/propostas")
public class PropostaController {
    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private StatusRouter statusRouter;

    @Autowired
    private CartaoRouter cartaoRouter;

    private List<Proposta> propostas = new ArrayList<Proposta>();

    private final Logger logger = LoggerFactory.getLogger(PropostaController.class);

    @PostMapping
    @Transactional
    public ResponseEntity<PropostaResponse> cria(@RequestBody @Valid PropostaRequest form,
                                                 UriComponentsBuilder uriBuilder) {

        logger.info(String.format("Recebendo request com documento: %s", form.getDocumento()));

        Proposta proposta = form.toModel();

        manager.persist(proposta);

        logger.info(String.format("Request persistido com id: %d", proposta.getId()));

        URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();

        try {

            logger.info("Enviando requisição para analise financeira");

            StatusResponse response = statusRouter.status(proposta.toStatus());

            proposta.setStatus(response.getResultadoSolicitacao());

            logger.info(String.format("Proposta com id: %d é ELEGIVEL para cartão", proposta.getId()));

            propostas.add(proposta);

        } catch (FeignException e) {
            logger.info(String.format("Proposta com id: %d não é ELEGIVEL para cartão", proposta.getId()));
            proposta.setStatus(StatusEnum.COM_RESTRICAO);
        }

        return ResponseEntity.created(uri).body(new PropostaResponse(proposta));
    }

    @Transactional
    @Scheduled(fixedDelay = 5000)
    public void criaCartao() {

        logger.info(String.format("Solicitando cadastro de cartões, cadastros necessarios: %d", propostas.size()));
        while(propostas.size() > 0) {
            Proposta proposta = propostas.get(0);

            logger.info(String.format("Solicitnado cartão %d", proposta.getId()));

            CartaoResponseRouter cartaoResponse = cartaoRouter.criaCartao(proposta.toCartaoRequest());

            Cartao cartao = cartaoResponse.toModel(proposta);

            logger.info(String.format("Proposta id: %d cartão da proposta: %s", proposta.getId(), cartao.getNumeroCartao()));

            manager.merge(cartao);

            propostas.remove(0);

            logger.info(String.format("Propostas restantes: %d", propostas.size()));
        }

        logger.info("Cadastrou todos os cartões necessarios");

    }

    @GetMapping("/{id}")
    public ResponseEntity<PropostaResponse> buscaPorId(@PathVariable Long id) throws NotFoundException {
        Proposta proposta = Optional.ofNullable(manager.find(Proposta.class, id))
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Id da proposta não encontrado"
                        ));

        return ResponseEntity.ok(new PropostaResponse(proposta));

    }

}
