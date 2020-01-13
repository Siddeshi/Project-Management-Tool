package org.sid.tool.feature.services;

import org.sid.tool.customexception.BacklogNotFoundException;
import org.sid.tool.models.ProductBacklog;
import org.sid.tool.repos.ProductBacklogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation class for dao, it manages the transaction of product backlogs
 *
 * @author siddesh
 * @since 09/Jan/2020
 */
@Service
public class ProductBacklogServiceImpl implements ProductBacklogService {

    /**
     * Autowiring the repository
     */
    private ProductBacklogRepository backlogRepository;

    @Autowired
    public ProductBacklogServiceImpl(ProductBacklogRepository backlogRepository) {
        this.backlogRepository = backlogRepository;
    }

    public ProductBacklogServiceImpl() {
    }

    /**
     * Add a new backlog to repository
     *
     * @param productBacklog new product backlog
     * @return ProductBacklog
     */
    @Override
    public ProductBacklog createNewBacklog(ProductBacklog productBacklog) {
        return backlogRepository.save(productBacklog);
    }

    /**
     * Delete a backlog from repo using id
     *
     * @param id id of the backlog
     */
    @Override
    public void deleteBacklog(String id) {
        backlogRepository.delete(id);
    }

    /**
     * Fetch backlog by its id
     *
     * @param id id of the backlog
     * @return ProductBacklog
     */
    @Override
    public ProductBacklog findProductBacklogById(String id) {
        return backlogRepository.findOne(id);
    }

    /**
     * Update the existing product backlog
     * @param backlog product backlog
     */
    @Override
    public void updateBacklog(ProductBacklog backlog) {
        ProductBacklog productBacklog = this.findProductBacklogById(backlog.get_id());
        if (productBacklog != null) {
            backlogRepository.save(backlog);
        } else {
            throw new BacklogNotFoundException("Backlog not found for the id-" + backlog.get_id());
        }
    }
}
