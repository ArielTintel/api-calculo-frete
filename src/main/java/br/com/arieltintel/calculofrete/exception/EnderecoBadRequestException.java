package br.com.arieltintel.calculofrete.exception;

public class EnderecoBadRequestException extends RuntimeException {

    public EnderecoBadRequestException() {
        super("Cep não encontrado.");
    }

    public EnderecoBadRequestException(String cep) {
        super("Cep: " + cep + " não encontrado ou Inválido");
    }

}