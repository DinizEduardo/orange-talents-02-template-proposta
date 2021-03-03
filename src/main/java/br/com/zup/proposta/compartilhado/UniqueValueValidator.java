package br.com.zup.proposta.compartilhado;

import br.com.zup.proposta.compartilhado.excpetion.UniqueValueException;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collections;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    private String domainAttribute;
    private Class<?> klass;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(UniqueValue params) {
        domainAttribute = params.fieldName();
        klass = params.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) throws HttpClientErrorException.UnprocessableEntity {
        Query query = manager.createQuery("select 1 from " +
                klass.getName() + " where "
                + domainAttribute +
                " = :value");

        query.setParameter("value", value);

//        List<?> list = query.getResultList();

//        Assert.isTrue(list.size() <=1, "Foi encontrado mais de um " + klass + " com o atributo" + domainAttribute);

        try{
            query.getSingleResult();
            throw new UniqueValueException(Collections.singletonMap(domainAttribute, domainAttribute + " já está cadastrado"));
        }catch (NoResultException e){
            return true;
        }


//        return list.isEmpty();
    }
}
