package org.sid.tool.projects.services;

import org.sid.tool.models.ProjectDetails;

import java.util.List;


/**
 * Dao interface for projects transaction handling.
 */
public interface ProjectDetailsService {

    ProjectDetails getProjectById(String id);

    ProjectDetails getProjectByProjectName(String name);

    List<ProjectDetails> getAllProjects();

    ProjectDetails createNewProject(ProjectDetails projectDetails);

    void deleteProjectById(String id);

    List<ProjectDetails> searchProjects(String searchStr);

    void updateProjectDetails(ProjectDetails projectDetails);
}
