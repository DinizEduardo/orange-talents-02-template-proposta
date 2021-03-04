package br.com.zup.proposta.propostas;

import br.com.zup.proposta.cartoes.Cartao;
import br.com.zup.proposta.cartoes.CartaoResponse;
import br.com.zup.proposta.cartoes.CartaoRouter;
import br.com.zup.proposta.status.StatusEnum;
import br.com.zup.proposta.status.StatusResponse;
import br.com.zup.proposta.status.StatusRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
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
import java.util.ArrayList;
import java.util.List;

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

    @PostMapping
    @Transactional
    public ResponseEntity<PropostaResponse> cria(@RequestBody @Valid PropostaRequest form,
                                                 UriComponentsBuilder uriBuilder) {

        Proposta proposta = form.toModel();

        manager.persist(proposta);

        URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();

        StatusResponse response = statusRouter.status(proposta.toStatus());
        proposta.setStatus(response.getResultadoSolicitacao());

        if(proposta.getStatus() == PropostaStatusEnum.ELEGIVEL)
            propostas.add(proposta);


        return ResponseEntity.created(uri).body(new PropostaResponse(proposta));
    }

    @Transactional
    @Scheduled(fixedDelay = 5000)
    public void criaCartao() {

        System.out.println("Entrou no Scheduled");

        while(propostas.size() > 0) {
            Proposta proposta = propostas.get(0);
            System.out.println("Cadastrando cartão da proposta: " + proposta.getId());
            CartaoResponse cartaoResponse = cartaoRouter.criaCartao(proposta.toCartaoRequest());

            Cartao cartao = cartaoResponse.toModel(proposta);

            manager.merge(cartao);

            propostas.remove(0);

            System.out.println("Quantiade de propostas -> " + propostas.size());
        }

        System.out.println("Saiu do Scheduled");

    }

}
