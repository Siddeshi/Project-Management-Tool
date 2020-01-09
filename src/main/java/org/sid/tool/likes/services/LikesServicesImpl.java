package org.sid.tool.likes.services;

import org.sid.tool.customexception.LikeNotFoundException;
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

@Service
@Transactional
public class LikesServicesImpl implements LikesServices {

    private final LikesRepository likesRepository;

    private final ProjectDetailsService projectDetailsService;

    private final ProductBacklogService backlogService;

    public LikesServicesImpl(LikesRepository likesRepository, ProjectDetailsService projectDetailsService,
                             ProductBacklogService backlogService) {
        this.likesRepository = likesRepository;
        this.projectDetailsService = projectDetailsService;
        this.backlogService = backlogService;
    }

    @Autowired


    @Override
    public List<Like> getProjectLikesList(String projectId) {
        return likesRepository.findByProjectId(projectId);
    }

    @Override
    public List<Like> getFeatureLikesList(String featureId) {
        return likesRepository.findByFeatureId(featureId);
    }

    @Override
    public long countProjectLikes(String projectId) {
        List<Like> likes = this.getProjectLikesList(projectId);
        return likes.size();
    }

    @Override
    public long countFeatureLikes(String featureId) {
        List<Like> likes = this.getFeatureLikesList(featureId);
        return likes.size();
    }

    @Override
    public boolean checkProjectLiked(String projectId, String userId) {

        Like like = likesRepository.findByProjectIdAndUserId(projectId, userId);
        return like != null;
    }

    @Override
    public boolean checkFeatureLiked(String featureid, String userId) {
        Like like = likesRepository.findByFeatureIdAndUserId(featureid, userId);
        return like != null;
    }


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

    @Override
    public String unlikeFeature(String featureId, String userid) {
        if (this.checkFeatureLiked(featureId, userid)) {
            Like like = this.getLikedFeature(featureId, userid);
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

    @Override
    public Like getLikedProject(String projectId, String userId) {
        return likesRepository.findByProjectIdAndUserId(projectId, userId);
    }

    @Override
    public Like getLikedFeature(String featureId, String userId) {
        return likesRepository.findByFeatureIdAndUserId(featureId, userId);
    }

    @Override
    public void deleteLike(String id) {
        likesRepository.delete(id);
    }
}
