package br.com.zup.proposta.propostas;

import br.com.zup.proposta.status.StatusResponse;
import br.com.zup.proposta.status.StatusRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/propostas")
public class PropostaController {
    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private StatusRouter statusRouter;

    @PostMapping
    @Transactional
    public ResponseEntity<PropostaResponse> cria(@RequestBody @Valid PropostaRequest form,
                                                 UriComponentsBuilder uriBuilder) {

        Proposta proposta = form.toModel();

        manager.persist(proposta);

        URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();

        StatusResponse response = statusRouter.status(proposta.toStatus());
        proposta.setStatus(response.getResultadoSolicitacao());

        return ResponseEntity.created(uri).body(new PropostaResponse(proposta));
    }

}
