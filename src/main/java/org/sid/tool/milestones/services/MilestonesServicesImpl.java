package org.sid.tool.milestones.services;

import org.sid.tool.models.Milestone;
import org.sid.tool.repos.MilestonesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Dao implementation class, it is to add new milestone,
 * delete milestone, check if it exists or update if any changes are made
 *
 * @author siddesh
 * @since 12/Jan/2020
 */
@Service
@Transactional
public class MilestonesServicesImpl implements MilestonesServices {

    /**
     * Autowiring the milestone repo
     */
    private MilestonesRepository milestonesRepository;

    @Autowired
    public MilestonesServicesImpl(MilestonesRepository milestonesRepository) {
        this.milestonesRepository = milestonesRepository;
    }

    public MilestonesServicesImpl() {
    }

    /**
     * Method is for checking if the milestone is exist with this id
     *
     * @param milestoneId id of the milestone
     * @return boolean true/false
     */
    @Override
    public boolean checkMilestoneExist(String milestoneId) {
        return milestonesRepository.exists(milestoneId);
    }

    /**
     * Method is to create a new milestone
     *
     * @param milestone milestone object
     */
    @Override
    public void createNewMilestone(Milestone milestone) {
        milestonesRepository.save(milestone);
    }

    /**
     * Method is to delete the milestone using id
     *
     * @param milestoneId
     */
    @Override
    public void deleteMilestone(String milestoneId) {
        milestonesRepository.delete(milestoneId);
    }

    /**
     * Method is for updating the existing milestone
     *
     * @param milestone
     */
    @Override
    public void updateMilestone(Milestone milestone) {
        milestonesRepository.save(milestone);
    }
}
