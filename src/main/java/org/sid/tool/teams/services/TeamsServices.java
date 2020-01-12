package org.sid.tool.teams.services;

import org.sid.tool.models.Team;

import java.util.List;

/**
 * Dao layer is for team management
 *
 * @author siddesh
 * @since 09/Jan/2020
 */
public interface TeamsServices {

    Team findTeamById(String id);

    List<Team> listAllTeams();

    void addNewTeam(Team team);

    boolean checkTeamExistsByName(String name);

    void deleteTeam(String id);
}
