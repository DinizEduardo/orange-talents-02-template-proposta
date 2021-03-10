package br.com.zup.proposta.propostas.validator;

import br.com.zup.proposta.compartilhado.excpetion.UniqueValueException;
import com.google.common.hash.Hashing;
import org.springframework.web.client.HttpClientErrorException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

public class DocumentoUnicoValidator implements ConstraintValidator<DocumentoUnico, Object> {

    private String domainAttribute;
    private Class<?> klass;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(DocumentoUnico params) {
        domainAttribute = params.fieldName();
        klass = params.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) throws HttpClientErrorException.UnprocessableEntity {
        Query query = manager.createQuery("select 1 from " +
                klass.getName() + " where "
                + domainAttribute +
                " = :value");

        query.setParameter("value", Hashing.sha256()
                .hashString((CharSequence) value, StandardCharsets.UTF_8)
                .toString());

        try{
            query.getSingleResult();
            throw new UniqueValueException(Collections.singletonMap("documento", "já está cadastrado"));
        }catch (NoResultException e){
            return true;
        }


//        return list.isEmpty();
    }
}
