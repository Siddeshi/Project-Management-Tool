package org.sid.tool.repos;

import org.sid.tool.models.ProductBacklog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductBacklogRepository extends MongoRepository<ProductBacklog, String> {
}
