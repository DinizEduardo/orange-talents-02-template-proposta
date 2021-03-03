package br.com.zup.proposta.compartilhado.excpetion;

import java.util.Map;

public class UniqueValueException extends RuntimeException {
    private final Map<String, Object> messages;

    public UniqueValueException(Map<String, Object> messages) {
        super("Validation error in multiple fields");

        this.messages = messages;
    }

    public Map<String, Object> getMessages() {
        return messages;
    }
}