package org.sid.tool.milestones.services;

import org.sid.tool.models.Milestone;

/**
 * Dao interface for milestone management
 *
 * @author siddesh
 * @since 12/Jan/2020
 */
public interface MilestonesServices {

    boolean checkMilestoneExist(String milestoneId);

    void createNewMilestone(Milestone milestone);

    void deleteMilestone(String milestoneId);

    void updateMilestone(Milestone milestone);
}
