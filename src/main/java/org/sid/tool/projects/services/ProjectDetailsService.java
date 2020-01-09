package org.sid.tool.projects.services;

import org.sid.tool.models.ProjectDetails;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProjectDetailsService {

    ResponseEntity<List<ProjectDetails>> getAllProjects();

    ResponseEntity<ProjectDetails> createNewProject(ProjectDetails projectDetails);
}
