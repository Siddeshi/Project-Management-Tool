package org.sid.tool.likes.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.sid.tool.customexception.FeatureNotFoundException;
import org.sid.tool.customexception.ProjectNotFoundException;
import org.sid.tool.customexception.UserNotFoundException;
import org.sid.tool.likes.services.LikesServices;
import org.sid.tool.projects.services.ProjectDetailsService;
import org.sid.tool.user.services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/")
@Api(value = "tool", description = "Operations pertaining to Likes details")
public class LikesController {

    private final LikesServices likesServices;

    private final UserDetailsService userDetailsService;

    private final ProjectDetailsService projectDetailsService;

    @Autowired
    public LikesController(LikesServices likesServices, UserDetailsService userDetailsService, ProjectDetailsService projectDetailsService) {
        this.likesServices = likesServices;
        this.userDetailsService = userDetailsService;
        this.projectDetailsService = projectDetailsService;
    }


    @PostMapping(value = "/like/project/{projectId}")
    @ApiOperation(value = "Like project",
            notes = "", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "liked", response = String.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public String likeProject(@PathVariable String projectId, @RequestParam String userId) {

        if (userDetailsService.findUserById(userId) == null) {
            throw new UserNotFoundException("User not found for the given userid-" + userId);
        } else {
            if (projectDetailsService.getProjectById(projectId) == null) {
                throw new ProjectNotFoundException("Project not found for the given projectId-" + projectId);
            } else {
                return likesServices.likeProject(projectId, userId);
            }
        }
    }

    @PutMapping(value = "/unlike/project/{projectId}")
    @ApiOperation(value = "Unlike project",
            notes = "", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "unliked", response = String.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public String unlikeProject(@PathVariable String projectId, @RequestParam String userId) {

        if (userDetailsService.findUserById(userId) == null) {
            throw new UserNotFoundException("User not found for the given userid-" + userId);
        } else {
            if (projectDetailsService.getProjectById(projectId) == null) {
                throw new ProjectNotFoundException("Project not found for the given projectId-" + projectId);
            } else {
                return likesServices.unlikeProject(projectId, userId);
            }
        }
    }

    @PostMapping(value = "/like/feature/{featureId}")
    @ApiOperation(value = "Like feature",
            notes = "", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "liked", response = String.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public String likeFeature(@PathVariable String featureId, @RequestParam String userId) {
        if (userDetailsService.findUserById(userId) == null) {
            throw new UserNotFoundException("User not found for the given userid-" + userId);
        } else {
            if (true) {
                throw new FeatureNotFoundException("Feature not found for the feature id-" + featureId);
            } else {
                return likesServices.unlikeFeature(featureId, userId);
            }
        }
    }

    @PutMapping(value = "/unlike/feature/{featureId}")
    @ApiOperation(value = "Unlike feature",
            notes = "", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "unliked", response = String.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public String unlikeFeature(@PathVariable String featureId, @RequestParam String userId) {
        if (userDetailsService.findUserById(userId) == null) {
            throw new UserNotFoundException("User not found for the given userid-" + userId);
        } else {
            if (true) {
                throw new FeatureNotFoundException("Feature not found for the feature id-" + featureId);
            } else {
                return likesServices.likeFeature(featureId, userId);
            }
        }
    }
}
