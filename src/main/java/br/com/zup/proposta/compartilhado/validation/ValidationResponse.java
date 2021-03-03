package br.com.zup.proposta.compartilhado.validation;

public class ValidationResponse {
    private final String field;
    private final Object message;

    public ValidationResponse(String field, Object message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public Object getMessage() {
        return message;
    }
}