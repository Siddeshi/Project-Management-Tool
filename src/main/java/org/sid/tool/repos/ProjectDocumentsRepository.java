package org.sid.tool.repos;

import org.sid.tool.models.ProjectDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectDocumentsRepository extends MongoRepository<ProjectDocument, String> {
}
