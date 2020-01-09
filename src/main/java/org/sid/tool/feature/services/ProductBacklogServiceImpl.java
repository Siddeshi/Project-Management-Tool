package org.sid.tool.feature.services;

import org.sid.tool.customexception.BacklogNotFoundException;
import org.sid.tool.models.ProductBacklog;
import org.sid.tool.repos.ProductBacklogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductBacklogServiceImpl implements ProductBacklogService {

    private final ProductBacklogRepository backlogRepository;

    public ProductBacklogServiceImpl(ProductBacklogRepository backlogRepository) {
        this.backlogRepository = backlogRepository;
    }

    @Override
    public ProductBacklog createNewBacklog(ProductBacklog productBacklog) {
        return backlogRepository.save(productBacklog);
    }

    @Override
    public void deleteBacklog(String id) {
        backlogRepository.delete(id);
    }

    @Override
    public ProductBacklog findProductBacklogById(String id) {
        return backlogRepository.findOne(id);
    }

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
