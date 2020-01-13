package org.sid.tool.likes.services;

import org.sid.tool.customexception.BacklogNotFoundException;
import org.sid.tool.customexception.LikeNotFoundException;
import org.sid.tool.customexception.ProjectNotFoundException;
import org.sid.tool.feature.services.ProductBacklogService;
import org.sid.tool.models.Like;
import org.sid.tool.models.ProductBacklog;
import org.sid.tool.models.ProjectDetails;
import org.sid.tool.projects.services.ProjectDetailsService;
import org.sid.tool.repos.LikesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation class for dao interface, it manages the project and backlog transactions
 *
 * @author siddesh
 * @since 09/Jan/2020
 */
@Service
@Transactional
public class LikesServicesImpl implements LikesServices {

    /**
     * Autowiring the likes repo
     */
    private LikesRepository likesRepository;

    /**
     * Autowiring the project details dao interface
     */
    private ProjectDetailsService projectDetailsService;

    /**
     * Autowiring the backlog dao interface
     */
    private ProductBacklogService backlogService;

    @Autowired
    public LikesServicesImpl(LikesRepository likesRepository, ProjectDetailsService projectDetailsService,
                             ProductBacklogService backlogService) {
        this.likesRepository = likesRepository;
        this.projectDetailsService = projectDetailsService;
        this.backlogService = backlogService;
    }

    public LikesServicesImpl() {
    }

    /**
     * Get list of all likes on the project
     *
     * @param projectId id of the project
     * @return List<Like> returns list of likes
     */
    @Override
    public List<Like> getProjectLikesList(String projectId) {
        return likesRepository.findByProjectId(projectId);
    }

    /**
     * Get list of all likes on the feature
     *
     * @param featureId id of the feature
     * @return List<Like> list of likes
     */
    @Override
    public List<Like> getFeatureLikesList(String featureId) {
        return likesRepository.findByFeatureId(featureId);
    }

    /**
     * To check whether the project is liked by the user
     * @param projectId id of the project
     * @param userId id of the user
     * @return boolean true/false
     */
    @Override
    public boolean checkProjectLiked(String projectId, String userId) {

        Like like = likesRepository.findByProjectIdAndUserId(projectId, userId);
        return like != null;
    }

    /**
     * To check whether the feature is liked by user or not
     * @param featureId id of the feature
     * @param userId id of the user
     * @return boolean true/false
     */
    @Override
    public boolean checkFeatureLiked(String featureId, String userId) {
        Like like = likesRepository.findByFeatureIdAndUserId(featureId, userId);
        return like != null;
    }


    /**
     * When user likes the project and an entry
     * @param projectId id of the project
     * @param userId id of the user
     * @return String status
     */
    @Override
    public String likeProject(String projectId, String userId) {
        if (this.checkProjectLiked(projectId, userId)) {
            return "already liked";
        } else {
            Like like = new Like();
            like.setProjectId(projectId);
            like.setUserId(userId);
            likesRepository.save(like);
            ProjectDetails projectDetails = projectDetailsService.getProjectById(projectId);
            projectDetails.setLikesCount(projectDetails.getLikesCount() + 1);
            projectDetailsService.updateProjectDetails(projectDetails);
            return "liked";
        }
    }

    /**
     * When user likes the backlog add an entry
     * @param featureId id of the backlog
     * @param userId id of the user
     * @return String status
     */
    @Override
    public String likeFeature(String featureId, String userId) {
        if (this.checkFeatureLiked(featureId, userId)) {
            return "already liked";
        } else {
            Like like = new Like();
            like.setFeatureId(featureId);
            like.setUserId(userId);
            likesRepository.save(like);
            ProductBacklog productBacklog = backlogService.findProductBacklogById(featureId);
            productBacklog.setLikesCount(productBacklog.getLikesCount() + 1);
            backlogService.updateBacklog(productBacklog);
            return "liked";
        }
    }

    /**
     * When user unlike the project remove an entry from repo
     * @param projectId id of the project
     * @param userId id of the user
     * @return String status
     */
    @Override
    public String unlikeProject(String projectId, String userId) {
        if (this.checkProjectLiked(projectId, userId)) {
            Like like = this.getLikedProject(projectId, userId);
            if (like.getFeatureId() != null) {
                like.setProjectId(null);
            } else {
                this.deleteLike(like.get_id());
            }
            ProjectDetails projectDetails = projectDetailsService.getProjectById(projectId);
            projectDetails.setLikesCount(projectDetails.getLikesCount() - 1);
            projectDetailsService.updateProjectDetails(projectDetails);
        } else {
            throw new LikeNotFoundException("No like found for this project");
        }
        return "unliked";
    }

    /**
     * When user unlikes the backlog, remove an entry from repo
     * @param featureId id of the backlog
     * @param userId id of the user
     * @return String status
     */
    @Override
    public String unlikeFeature(String featureId, String userId) {
        if (this.checkFeatureLiked(featureId, userId)) {
            Like like = this.getLikedFeature(featureId, userId);
            if (like.getProjectId() != null) {
                like.setFeatureId(null);
            } else {
                this.deleteLike(like.get_id());
            }
            ProductBacklog productBacklog = backlogService.findProductBacklogById(featureId);
            productBacklog.setLikesCount(productBacklog.getLikesCount() - 1);
            backlogService.updateBacklog(productBacklog);
        } else {
            throw new LikeNotFoundException("No like found for this feature");
        }
        return "unliked";
    }

    /**
     * For getting project like entry from repo using
     * @param projectId id of the project
     * @param userId id of the user
     * @return Like
     */
    @Override
    public Like getLikedProject(String projectId, String userId) {
        return likesRepository.findByProjectIdAndUserId(projectId, userId);
    }

    /**
     * For getting backlog like entry from repo
     * @param featureId id of the backlog
     * @param userId id of the user
     * @return Like
     */
    @Override
    public Like getLikedFeature(String featureId, String userId) {
        return likesRepository.findByFeatureIdAndUserId(featureId, userId);
    }

    /**
     * Delete like entry of both project and backlog
     * @param id id of like
     */
    @Override
    public void deleteLike(String id) {
        likesRepository.delete(id);
    }

    /**
     * Delete all the likes from the project
     *
     * @param projectId id of the project
     */
    @Override
    public String deleteAllProjectLikes(String projectId) {
        ProjectDetails projectDetails = projectDetailsService.getProjectById(projectId);
        if (projectDetails == null) {
            throw new ProjectNotFoundException("Project is not exist");
        } else {
            List<Like> likes = likesRepository.findByProjectId(projectId);
            long likesCount = likes.size();
            for (Like like : likes
            ) {
                likesRepository.delete(like.get_id());
            }
            projectDetails.setLikesCount(likesCount);
            projectDetailsService.updateProjectDetails(projectDetails);
            return "Deleted " + likesCount + " likes";
        }
    }

    /**
     * Delete all the likes from backlog
     *
     * @param backlogId id of backlog
     * @return String returns count
     */
    public String deleteAllBacklogLikes(String backlogId) {
        ProductBacklog backlog = backlogService.findProductBacklogById(backlogId);
        if (backlog == null) {
            throw new BacklogNotFoundException("Backlog is not exist");
        } else {
            List<Like> likes = likesRepository.findByFeatureId(backlogId);
            long count = likes.size();
            for (Like like : likes) {
                likesRepository.delete(like);
            }
            backlog.setLikesCount(count);
            backlogService.updateBacklog(backlog);
            return "Deleted " + count + " likes";
        }
    }
}
