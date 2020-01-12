package org.sid.tool.customexception;

public class TeamModificationException extends RuntimeException {
    public TeamModificationException(String message) {
        super(message);
    }

    public TeamModificationException(String message, Throwable cause) {
        super(message, cause);
    }
}
