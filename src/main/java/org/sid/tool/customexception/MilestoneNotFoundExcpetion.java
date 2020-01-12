package org.sid.tool.customexception;

/**
 * Custom exception if milestone not found
 *
 * @author siddesh
 * @since 12/Jan/2020
 */
public class MilestoneNotFoundExcpetion extends RuntimeException {
    public MilestoneNotFoundExcpetion(String message) {
        super(message);
    }

    public MilestoneNotFoundExcpetion(String message, Throwable cause) {
        super(message, cause);
    }
}
