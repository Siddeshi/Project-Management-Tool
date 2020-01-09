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

@RestController
@RequestMapping(value = "/")
public class TeamsController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private final TeamsServices teamsServices;

    public TeamsController(TeamsServices teamsServices) {
        this.teamsServices = teamsServices;
    }

    @RequestMapping(value = "/teams/list", method = RequestMethod.GET)
    public ResponseEntity<List<Team>> listAllTeams() {
        return teamsServices.listAllTeams();
    }

    @PostMapping(value = "/teams/new")
    public ResponseEntity<Team> addNewTeam(@Valid @RequestBody Team team) {
        return teamsServices.addNewTeam(team);
    }

    @GetMapping(value = "/teams/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable String id) {
        return teamsServices.findTeamById(id);
    }
}
