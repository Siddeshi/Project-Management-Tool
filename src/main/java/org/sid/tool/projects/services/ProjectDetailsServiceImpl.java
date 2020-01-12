package org.sid.tool.projects.services;

import org.sid.tool.customexception.ProjectNotFoundException;
import org.sid.tool.models.ProjectDetails;
import org.sid.tool.repos.ProjectDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Dao layer implementation class is for maintaining the transactions of the project
 *
 * @author siddesh
 * @since 08/Jan/2020
 */
@Service
public class ProjectDetailsServiceImpl implements ProjectDetailsService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Autowiring the project repository
     */
    private final ProjectDetailsRepository projectDetailsRepository;


    /**
     * Autowiring the default mongo template
     */
    private final MongoTemplate mongoTemplate;

    @Autowired
    public ProjectDetailsServiceImpl(ProjectDetailsRepository projectDetailsRepository, MongoTemplate mongoTemplate) {
        this.projectDetailsRepository = projectDetailsRepository;
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * To fetch all the projects
     *
     * @return List<ProjectDetails> list of all the projects
     */
    @Override
    @Transactional
    public List<ProjectDetails> getAllProjects() {
        return projectDetailsRepository.findAll();
    }

    /**
     * To create a new project
     *
     * @param projectDetails accepts the project details
     * @return ProjectDetails
     */
    @Override
    @Transactional
    public ProjectDetails createNewProject(ProjectDetails projectDetails) {
        return projectDetailsRepository.save(projectDetails);
    }

    /**
     * To get the project by its id
     *
     * @param id id of the project
     * @return ProjectDetails
     */
    @Override
    @Transactional
    public ProjectDetails getProjectById(String id) {
        return projectDetailsRepository.findOne(id);
    }

    /**
     * To delete the project by its id
     * @param id id of the project
     */
    @Override
    @Transactional
    public void deleteProjectById(String id) {
        projectDetailsRepository.delete(id);
    }

    /**
     * To search the project by query
     * @param searchStr substring of the project, case sensitive
     * @return List<ProjectDetails> list of all projects that matches with query
     */
    @Override
    public List<ProjectDetails> searchProjects(String searchStr) {
        Query query = new Query();
        query.limit(10);
        query.addCriteria(Criteria.where("projectName").regex(searchStr));
        return mongoTemplate.find(query, ProjectDetails.class);
    }

    /**
     * To update the existing project
     * @param projectDetails updated project details
     * @exception ProjectNotFoundException when id of the project not found in the repo
     */
    @Override
    public void updateProjectDetails(ProjectDetails projectDetails) {
        ProjectDetails updateProject = this.getProjectById(projectDetails.get_id());
        if (updateProject != null) {
            projectDetailsRepository.save(projectDetails);
        } else {
            throw new ProjectNotFoundException("Project not found for the id-" + projectDetails.get_id());
        }
    }
}
