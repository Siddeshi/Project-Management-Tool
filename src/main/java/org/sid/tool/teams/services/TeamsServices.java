package org.sid.tool.teams.services;

import org.sid.tool.models.Team;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Dao layer is for team management
 *
 * @author siddesh
 * @since 09/Jan/2020
 */
public interface TeamsServices {

    ResponseEntity<Team> findTeamById(String id);

    ResponseEntity<List<Team>> listAllTeams();

    ResponseEntity<Team> addNewTeam(Team team);
}
