package org.sid.tool.repos;

import org.sid.tool.models.ProjectDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectDetailsRepository extends MongoRepository<ProjectDetails, String> {

}
