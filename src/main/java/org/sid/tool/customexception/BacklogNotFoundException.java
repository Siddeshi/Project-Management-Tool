package org.sid.tool.customexception;

public class BacklogNotFoundException extends RuntimeException {

    public BacklogNotFoundException(String message) {
        super(message);
    }

    public BacklogNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
