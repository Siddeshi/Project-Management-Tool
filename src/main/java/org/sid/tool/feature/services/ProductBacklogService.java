package org.sid.tool.feature.services;

import org.sid.tool.models.ProductBacklog;

public interface ProductBacklogService {

    ProductBacklog createNewBacklog(ProductBacklog productBacklog);

    void deleteBacklog(String id);

    ProductBacklog findProductBacklogById(String id);

    void updateBacklog(ProductBacklog backlog);
}
