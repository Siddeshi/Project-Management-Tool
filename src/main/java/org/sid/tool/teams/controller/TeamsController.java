package org.sid.tool.teams.controller;

import org.sid.tool.customexception.TeamModificationException;
import org.sid.tool.customexception.TeamNotFoundExeption;
import org.sid.tool.models.Team;
import org.sid.tool.teams.services.TeamsServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        List<Team> teamList = teamsServices.listAllTeams();
        if (teamList.size() == 0 || teamList == null) {
            throw new TeamNotFoundExeption(" Team list is empty");
        } else
            return new ResponseEntity<>(teamList, HttpStatus.OK);
    }

    /**
     * Api is to add a new team
     *
     * @param team accepts valid team object
     * @return Team team detail
     */
    @PostMapping(value = "/teams/new")
    public ResponseEntity<String> addNewTeam(@Valid @RequestBody Team team, @RequestParam String teamName, @RequestParam String userId) {

        if (teamsServices.checkTeamExistsByName(teamName)) {
            throw new TeamModificationException("Team with this name is alrady exist");
        } else {
            teamsServices.addNewTeam(team);
            return new ResponseEntity<>("Added new team", HttpStatus.OK);
        }
    }

    /**
     * Api is to get the team details based on team id
     *
     * @param id id of the team
     * @return Team team detail
     */
    @GetMapping(value = "/teams/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable String id) {
        Team team = teamsServices.findTeamById(id);
        if (team == null) {
            throw new TeamNotFoundExeption("Team with this id is not found-" + id);
        } else {
            return new ResponseEntity<>(team, HttpStatus.OK);
        }
    }

    /**
     * Delete the team by its id
     *
     * @param teamId team id
     * @return String
     */
    @DeleteMapping("/teams/delete")
    public ResponseEntity<String> deleteTeam(@RequestParam String teamId) {
        if (teamsServices.findTeamById(teamId) == null) {
            throw new TeamModificationException("Team is not exist");
        } else {
            teamsServices.deleteTeam(teamId);
            return new ResponseEntity<>("Deleted the team", HttpStatus.OK);
        }
    }
}
