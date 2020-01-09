package org.sid.tool.customexception;

public class ProjectModificationException extends RuntimeException {

    public ProjectModificationException(String message) {
        super(message);
    }

    public ProjectModificationException(String message, Throwable cause) {
        super(message, cause);
    }
}
