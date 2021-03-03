package br.com.zup.proposta.compartilhado.validation;

import br.com.zup.proposta.compartilhado.excpetion.UniqueValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationHandler {
    @Autowired
    private MessageSource ms;

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(UniqueValueException.class)
    public List<ValidationResponse> handleMultipleException(UniqueValueException e) {
        List<ValidationResponse> responses = new ArrayList<>();
        e.getMessages().forEach((field, messages) -> {
            responses.add(new ValidationResponse(field, messages));
        });

        return responses;
    }

    public String getLocalMessage(ObjectError e) {
        return ms.getMessage(e, LocaleContextHolder.getLocale());
    }
}