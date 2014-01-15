package com.techery.spares.statemachine;

public class InvalidTransitionException extends IllegalStateException {
    public InvalidTransitionException(String localizedMessage) {
        super(localizedMessage);
    }
}
