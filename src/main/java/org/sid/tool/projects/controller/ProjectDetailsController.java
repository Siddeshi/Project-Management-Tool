package org.sid.tool.projects.controller;

import org.sid.tool.models.ProjectDetails;
import org.sid.tool.projects.services.ProjectDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/")
public class ProjectDetailsController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private final ProjectDetailsService detailsService;

    public ProjectDetailsController(ProjectDetailsService detailsService) {
        this.detailsService = detailsService;
    }

    @GetMapping(value = "/projects/list")
    public ResponseEntity<List<ProjectDetails>> getAllProjects() {
        return detailsService.getAllProjects();
    }

    @PostMapping(value = "/projects/new")
    public ResponseEntity<ProjectDetails> createNewProject(@Valid @RequestBody ProjectDetails projectDetails) {
        return detailsService.createNewProject(projectDetails);
    }
}
