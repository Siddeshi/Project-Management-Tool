package org.sid.tool.feature.services;

import org.sid.tool.models.ProductBacklog;

/**
 * The ProductBacklogService is a Dao layer for product backlogs
 *
 * @author siddesh
 * @since 09/Jan/2020
 */
public interface ProductBacklogService {

    ProductBacklog createNewBacklog(ProductBacklog productBacklog);

    void deleteBacklog(String id);

    ProductBacklog findProductBacklogById(String id);

    void updateBacklog(ProductBacklog backlog);
}
