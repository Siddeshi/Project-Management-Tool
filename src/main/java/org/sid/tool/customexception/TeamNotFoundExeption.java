package org.sid.tool.customexception;

public class TeamNotFoundExeption extends RuntimeException {
    public TeamNotFoundExeption(String message) {
        super(message);
    }

    public TeamNotFoundExeption(String message, Throwable cause) {
        super(message, cause);
    }
}
