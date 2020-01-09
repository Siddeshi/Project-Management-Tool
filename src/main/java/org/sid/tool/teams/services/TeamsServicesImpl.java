package org.sid.tool.teams.services;

import org.sid.tool.models.Team;
import org.sid.tool.repos.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeamsServicesImpl implements TeamsServices {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private final TeamRepository teamRepository;

    public TeamsServicesImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<Team> findTeamById(String id) {
        return new ResponseEntity<>(teamRepository.findOne(id), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<List<Team>> listAllTeams() {
        return new ResponseEntity<>(teamRepository.findAll(), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<Team> addNewTeam(Team team) {
        return new ResponseEntity<>(teamRepository.save(team), HttpStatus.OK);
    }
}
