package org.sid.tool.teams.controller;

import org.sid.tool.models.Team;
import org.sid.tool.teams.services.TeamsServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * TeamsController is a @RestController class, it enables REST endpoints for managing teams
 *
 * @author siddesh
 * @since 09/jan/2020
 */
@RestController
@RequestMapping(value = "/")
public class TeamsController {

    private final Logger log = LoggerFactory.getLogger(getClass());


    /**
     * Autowiring Dao layer
     */
    private final TeamsServices teamsServices;

    @Autowired
    public TeamsController(TeamsServices teamsServices) {
        this.teamsServices = teamsServices;
    }

    /**
     * Api is to list all the teams
     *
     * @return List<Team> list of teams
     */
    @RequestMapping(value = "/teams/list", method = RequestMethod.GET)
    public ResponseEntity<List<Team>> listAllTeams() {
        return teamsServices.listAllTeams();
    }

    /**
     * Api is to add a new team
     *
     * @param team accepts valid team object
     * @return Team team detail
     */
    @PostMapping(value = "/teams/new")
    public ResponseEntity<Team> addNewTeam(@Valid @RequestBody Team team) {
        return teamsServices.addNewTeam(team);
    }

    /**
     * Api is to get the team details based on team id
     *
     * @param id id of the team
     * @return Team team detail
     */
    @GetMapping(value = "/teams/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable String id) {
        return teamsServices.findTeamById(id);
    }
}
