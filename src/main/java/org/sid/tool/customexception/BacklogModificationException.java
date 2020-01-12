package org.sid.tool.customexception;

public class BacklogModificationException extends RuntimeException {
    public BacklogModificationException(String message) {
        super(message);
    }

    public BacklogModificationException(String message, Throwable cause) {
        super(message, cause);
    }
}
