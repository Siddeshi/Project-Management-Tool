package org.sid.tool.projects.services;

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

@Service
public class ProjectDetailsServiceImpl implements ProjectDetailsService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final ProjectDetailsRepository projectDetailsRepository;

    private final MongoTemplate mongoTemplate;

    @Autowired
    public ProjectDetailsServiceImpl(ProjectDetailsRepository projectDetailsRepository, MongoTemplate mongoTemplate) {
        this.projectDetailsRepository = projectDetailsRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Autowired


    @Override
    @Transactional
    public List<ProjectDetails> getAllProjects() {
        return projectDetailsRepository.findAll();
    }

    @Override
    @Transactional
    public ProjectDetails createNewProject(ProjectDetails projectDetails) {
        return projectDetailsRepository.save(projectDetails);
    }

    @Override
    @Transactional
    public ProjectDetails getProjectById(String id) {
        return projectDetailsRepository.findOne(id);
    }

    @Override
    @Transactional
    public void deleteProjectById(String id) {
        projectDetailsRepository.delete(id);
    }

    @Override
    public List<ProjectDetails> searchProjects(String searchStr) {
        Query query = new Query();
        query.limit(10);
        query.addCriteria(Criteria.where("projectName").regex(searchStr));
        List<ProjectDetails> projectDetailsList = mongoTemplate.find(query, ProjectDetails.class);
        return projectDetailsList;
    }
}
