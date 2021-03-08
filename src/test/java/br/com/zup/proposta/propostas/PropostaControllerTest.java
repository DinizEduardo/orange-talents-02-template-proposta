//package br.com.zup.proposta.propostas;
//
//import br.com.zup.proposta.utils.MockMvcRequest;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import java.util.List;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@ActiveProfiles("test")
//@AutoConfigureDataJpa
//@Transactional
//class PropostaControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    private EntityManager manager;
//
//    @Test
//    public void deveriaBuscarAPropostaPeloId() throws Exception {
//        PropostaRequest request = new PropostaRequest("68351808032",
//                "Eduardo Diniz",
//                "eduardo.diniz@zup.com.br",
//                "Rua Costa Barros 2200",
//                1000);
//
//        Proposta proposta = request.toModel();
//
//        manager.persist(proposta);
//
//        MockMvcRequest.performGet(mockMvc, "/propostas/" + proposta.getId(), 200, objectMapper, null);
//
//        List<Proposta> propostas = manager.createQuery("SELECT p FROM Proposta p", Proposta.class).getResultList();
//
//        Assertions.assertTrue(propostas.size() == 1);
//    }
//
//    @Test
//    public void naoDeveriaAcharPropostaComIdInvalido() throws Exception {
//        PropostaRequest request = new PropostaRequest("68351808032",
//                "Eduardo Diniz",
//                "eduardo.diniz@zup.com.br",
//                "Rua Costa Barros 2200",
//                1000);
//
//        Proposta proposta = request.toModel();
//
//        manager.persist(proposta);
//
//        MockMvcRequest.performGet(mockMvc, "/propostas/" + proposta.getId() + 1, 404, objectMapper, null);
//
//        List<Proposta> propostas = manager.createQuery("SELECT p FROM Proposta p", Proposta.class).getResultList();
//
//        Assertions.assertTrue(propostas.size() == 1);
//    }
//
//    @Test
//    public void naoDeveriaCadastrarPropostaComDocumentoDuplicado() throws Exception {
//        PropostaRequest request = new PropostaRequest("68351808032",
//                "Eduardo Diniz",
//                "eduardo.diniz@zup.com.br",
//                "Rua Costa Barros 2200",
//                1000);
//
//        Proposta proposta = request.toModel();
//
//        manager.persist(proposta);
//
//        MockMvcRequest.performPost(mockMvc, "/propostas", 422, objectMapper, request);
//
//        List<Proposta> propostas = manager.createQuery("SELECT p FROM Proposta p", Proposta.class).getResultList();
//
//        Assertions.assertTrue(propostas.size() == 1);
//    }
//
//    @Test
//    public void deveriaCadastrarUmaProposta() throws Exception {
//
//        PropostaRequest request = new PropostaRequest("68351808032",
//                "Eduardo Diniz",
//                "eduardo.diniz@zup.com.br",
//                "Rua Costa Barros 2200",
//                1000);
//
//        MvcResult result = MockMvcRequest.performPost(mockMvc, "/propostas", 201, objectMapper, request);
//
//        List<Proposta> propostas = manager.createQuery("SELECT p FROM Proposta p", Proposta.class).getResultList();
//
//        Assertions.assertTrue(propostas.size() == 1);
//
//        Proposta proposta = propostas.get(0);
//
//        Assertions.assertAll(
//                () -> Assertions.assertEquals(proposta.getEmail(), request.getEmail()),
//                () -> Assertions.assertEquals(proposta.getDocumento(), request.getDocumento()),
//                () -> Assertions.assertEquals(proposta.getEndereco(), request.getEndereco()),
//                () -> Assertions.assertEquals(proposta.getSalario(), request.getSalario()),
//                () -> Assertions.assertEquals(proposta.getNome(), request.getNome()),
//                () -> Assertions.assertEquals(result.getResponse().getHeaders("location").get(0),
//                        "http://localhost/propostas/" + proposta.getId())
//        );
//
//    }
//
//    @Test
//    public void naoDeveriaCadastrarPropostaSemEmail() throws Exception {
//        PropostaRequest request = new PropostaRequest("68351808032",
//                "Eduardo Diniz",
//                "",
//                "Rua Costa Barros 2200",
//                1000);
//
//        MockMvcRequest.performPost(mockMvc, "/propostas", 400, objectMapper, request);
//
//        List<Proposta> propostas = manager.createQuery("SELECT p FROM Proposta p", Proposta.class).getResultList();
//
//        Assertions.assertTrue(propostas.size() == 0);
//    }
//
//    @Test
//    public void naoDeveriaCadastrarPropostaComEmailInvalido() throws Exception {
//        PropostaRequest request = new PropostaRequest("68351808032",
//                "Eduardo Diniz",
//                "eduardo.diniz",
//                "Rua Costa Barros 2200",
//                1000);
//
//        MockMvcRequest.performPost(mockMvc, "/propostas", 400, objectMapper, request);
//        List<Proposta> propostas = manager.createQuery("SELECT p FROM Proposta p", Proposta.class).getResultList();
//
//        Assertions.assertTrue(propostas.size() == 0);
//    }
//
//    @Test
//    public void naoDeveriaCadastrarPropostaSemEndereco() throws Exception {
//        PropostaRequest request = new PropostaRequest("68351808032",
//                "Eduardo Diniz",
//                "eduardo.diniz@zup.com.br",
//                "",
//                1000);
//
//        MockMvcRequest.performPost(mockMvc, "/propostas", 400, objectMapper, request);
//        List<Proposta> propostas = manager.createQuery("SELECT p FROM Proposta p", Proposta.class).getResultList();
//
//        Assertions.assertTrue(propostas.size() == 0);
//    }
//
//    @Test
//    public void naoDeveriaCadastrarPropostaComSalarioNegativo() throws Exception {
//        PropostaRequest request = new PropostaRequest("68351808032",
//                "Eduardo Diniz",
//                "eduardo.diniz@zup.com.br",
//                "Rua Costa Barros 2200",
//                -1000);
//
//        MockMvcRequest.performPost(mockMvc, "/propostas", 400, objectMapper, request);
//        List<Proposta> propostas = manager.createQuery("SELECT p FROM Proposta p", Proposta.class).getResultList();
//
//        Assertions.assertTrue(propostas.size() == 0);
//    }
//
//    @Test
//    public void naoDeveriaCadastrarPropostaSemDocumento() throws Exception {
//        PropostaRequest request = new PropostaRequest("",
//                "Eduardo Diniz",
//                "eduardo.diniz@zup.com.br",
//                "Rua Costa Barros 2200",
//                1000);
//
//        MockMvcRequest.performPost(mockMvc, "/propostas", 400, objectMapper, request);
//        List<Proposta> propostas = manager.createQuery("SELECT p FROM Proposta p", Proposta.class).getResultList();
//
//        Assertions.assertTrue(propostas.size() == 0);
//    }
//
//    @Test
//    public void naoDeveriaCadastrarPropostaComDocumentoInvalido() throws Exception {
//        PropostaRequest request = new PropostaRequest("68351808031",
//                "Eduardo Diniz",
//                "eduardo.diniz@zup.com.br",
//                "Rua Costa Barros 2200",
//                1000);
//
//        MockMvcRequest.performPost(mockMvc, "/propostas", 400, objectMapper, request);
//        List<Proposta> propostas = manager.createQuery("SELECT p FROM Proposta p", Proposta.class).getResultList();
//
//        Assertions.assertTrue(propostas.size() == 0);
//    }
//
//    @Test
//    public void naoDeveriaCadastrarPropostaSemNome() throws Exception {
//        PropostaRequest request = new PropostaRequest("68351808032",
//                "",
//                "eduardo.diniz@zup.com.br",
//                "Rua Costa Barros 2200",
//                1000);
//
//        MockMvcRequest.performPost(mockMvc, "/propostas", 400, objectMapper, request);
//        List<Proposta> propostas = manager.createQuery("SELECT p FROM Proposta p", Proposta.class).getResultList();
//
//        Assertions.assertTrue(propostas.size() == 0);
//    }
//
//}