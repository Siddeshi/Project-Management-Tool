package org.sid.tool.projects.services;

import org.sid.tool.models.ProjectDetails;
import org.sid.tool.repos.ProjectDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectDetailsServiceImpl implements ProjectDetailsService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private final ProjectDetailsRepository projectDetailsRepository;

    public ProjectDetailsServiceImpl(ProjectDetailsRepository projectDetailsRepository) {
        this.projectDetailsRepository = projectDetailsRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<List<ProjectDetails>> getAllProjects() {
        return new ResponseEntity<>(projectDetailsRepository.findAll(), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ProjectDetails> createNewProject(ProjectDetails projectDetails) {
        return new ResponseEntity<>(projectDetailsRepository.save(projectDetails), HttpStatus.OK);
    }
}
