package org.sid.tool.projects.services;

import org.sid.tool.models.ProjectDetails;

import java.util.List;

public interface ProjectDetailsService {

    ProjectDetails getProjectById(String id);

    List<ProjectDetails> getAllProjects();

    ProjectDetails createNewProject(ProjectDetails projectDetails);

    void deleteProjectById(String id);

    List<ProjectDetails> searchProjects(String searchStr);
}
