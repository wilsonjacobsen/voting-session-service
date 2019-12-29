package com.sicredi.votingsessionservice.exception;

/**
 * @author Douglas Z Rossi
 *
 */
public class PreconditionFailed extends Exception {

    private static final long serialVersionUID = -1594262379904808301L;

    private final String message;

    public PreconditionFailed(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
