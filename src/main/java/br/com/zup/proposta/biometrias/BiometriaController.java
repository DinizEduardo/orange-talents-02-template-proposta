package br.com.zup.proposta.biometrias;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/biometrias")
public class BiometriaController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/{idCartao}")
    @Transactional
    public ResponseEntity<BiometriaResponse> criar(@RequestBody @Valid BiometriaRequest form,
                                                   @PathVariable("idCartao") Long id,
                                                   UriComponentsBuilder uriBuilder) {

        Biometria biometria = form.toModel(id, manager);

        manager.persist(biometria);

        URI uri = uriBuilder.path("/biometrias/{id}").buildAndExpand(biometria.getId()).toUri();

        return ResponseEntity.created(uri).body(new BiometriaResponse(biometria));

    }

}
