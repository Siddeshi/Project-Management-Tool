package org.sid.tool.teams.services;

import org.sid.tool.models.Team;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TeamsServices {

    ResponseEntity<Team> findTeamById(String id);

    ResponseEntity<List<Team>> listAllTeams();

    ResponseEntity<Team> addNewTeam(Team team);
}
