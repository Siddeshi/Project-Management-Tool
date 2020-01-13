package org.sid.tool.teams.services;

import org.sid.tool.models.Team;
import org.sid.tool.repos.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The Dao implementation class
 *
 * @author siddesh
 * @since 09/jan/2020
 */
@Service
public class TeamsServicesImpl implements TeamsServices {

    private final Logger log = LoggerFactory.getLogger(getClass());


    private TeamRepository teamRepository;

    @Autowired
    public TeamsServicesImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public TeamsServicesImpl() {
    }

    /**
     * Find the team by id
     *
     * @param id id of the team
     * @return Team team details
     */
    @Override
    @Transactional
    public Team findTeamById(String id) {
        return teamRepository.findOne(id);
    }

    /**
     * Fetch all the teams
     *
     * @return List<Team>
     */
    @Override
    @Transactional
    public List<Team> listAllTeams() {
        return teamRepository.findAll();
    }

    /**
     * Add a new team
     *
     * @param team team details
     * @return Team
     */
    @Override
    @Transactional
    public void addNewTeam(Team team) {
        teamRepository.save(team);
    }

    /**
     * check if the team is already exist with this name
     *
     * @param name name of the team
     * @return boolean true/false
     */
    @Override
    public boolean checkTeamExistsByName(String name) {
        return teamRepository.existsByTeamName(name);
    }

    /**
     * Delete the team by its id
     *
     * @param id team id
     */
    @Override
    public void deleteTeam(String id) {
        teamRepository.delete(id);
    }
}
