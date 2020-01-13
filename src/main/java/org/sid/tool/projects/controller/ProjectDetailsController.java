package org.sid.tool.projects.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.sid.tool.customexception.ProjectModificationException;
import org.sid.tool.customexception.ProjectNotFoundException;
import org.sid.tool.customexception.UserNotFoundException;
import org.sid.tool.likes.services.LikesServices;
import org.sid.tool.models.ProjectDetails;
import org.sid.tool.projects.services.ProjectDetailsService;
import org.sid.tool.user.services.UserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * The ProjectDetailsController is the @RestController class that exposes rest endpoints
 * for the end users to handle the projects
 *
 * @author siddesh
 * @since 08/Jan/2020
 */
@RestController
@RequestMapping(value = "/")
@Api(value = "tool", description = "Operations pertaining to project details")
public class ProjectDetailsController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * Autowiring project details dao layer
     */
    private final ProjectDetailsService detailsService;

    /**
     * Autowiring likes dao layer
     */
    private final LikesServices likesServices;

    /**
     * Autowiring user details dao layer
     */
    private final UserDetailsService userDetailsService;

    @Autowired
    public ProjectDetailsController(ProjectDetailsService detailsService, LikesServices likesServices, UserDetailsService userDetailsService) {
        this.detailsService = detailsService;
        this.likesServices = likesServices;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Api is to list all the projects
     *
     * @return List<ProjectDetails> list of projects
     * @throws ProjectNotFoundException throws this when list is empty
     */
    @GetMapping(value = "/projects/list", produces = "application/json")
    @ApiOperation(value = "List all projects", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list", response = ProjectDetails.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<List<ProjectDetails>> getAllProjects() {
        List<ProjectDetails> projectsList = detailsService.getAllProjects();
        if (projectsList.size() == 0)
            throw new ProjectNotFoundException("projects list is empty");
        else
            return new ResponseEntity<>(projectsList, HttpStatus.OK);
    }

    /**
     * Api is to create a new project by user
     * user id must exist in the database
     *
     * @param projectDetails accepts projects details as input request
     * @return String status
     */
    @PostMapping(value = "/projects/new", consumes = "application/json")
    @ApiOperation(value = "Add new project", response = ProjectDetails.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully added new project", response = ProjectDetails.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<String> createNewProject(@Valid @RequestBody ProjectDetails projectDetails, @RequestParam String projectName, @RequestParam String userId) {
        if (userDetailsService.findUserById(userId) == null) {
            throw new UserNotFoundException("User is not exist ");
        } else {
            if (detailsService.getProjectByProjectName(projectName) != null) {
                throw new ProjectModificationException("Project with this name is already exist-" + projectName);
            } else {
                detailsService.createNewProject(projectDetails);
                return new ResponseEntity<>("added a new project", HttpStatus.CREATED);
            }
        }
    }

    /**
     * Api is to delete a project based on its id
     *
     * @param projectId id of the project
     * @return String status
     * @throws ProjectNotFoundException throws when id doesn't exist
     */
    @DeleteMapping(value = "/projects/delete/{projectId}")
    @ApiOperation(value = "Delete project", response = ProjectDetails.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted the project", response = ProjectDetails.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<String> deleteProject(@PathVariable String projectId, @RequestParam String userId) {
        if (userDetailsService.findUserById(userId) == null) {
            throw new UserNotFoundException("User is not exist");
        } else {
            ProjectDetails oldProject = detailsService.getProjectById(projectId);
            if (oldProject == null) {
                throw new ProjectNotFoundException("project not found for the id-" + projectId);
            } else {
                detailsService.deleteProjectById(projectId);
                return new ResponseEntity<>("project deleted", HttpStatus.OK);
            }
        }
    }

    /**
     * Api is to search for projects by name
     * @param query substring of project name
     * @return List<ProjectDetails> it returns all the projects that matches with with query
     */
    @GetMapping(value = "/projects", produces = "application/json")
    @ApiOperation(value = "Search projects", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Projects found for the given criteria", response = ProjectDetails.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<List<ProjectDetails>> searchForProject(@RequestParam String query) {
        List<ProjectDetails> projectDetailsList = detailsService.searchProjects(query);
        if (projectDetailsList == null || projectDetailsList.size() == 0) {
            throw new ProjectNotFoundException("No projects found for the given query");
        } else {
            return new ResponseEntity<>(projectDetailsList, HttpStatus.OK);
        }
    }

    /**
     * APi is to fetch a particular project based on its id
     * @param projectId id of the project
     * @param userId id of the user who wants to access
     * @return ProjectDetails
     * @exception UserNotFoundException throws if the user is not found
     * @exception ProjectNotFoundException throws if the project is not found
     */
    @GetMapping(value = "/projects/{projectId}", produces = "application/json")
    @ApiOperation(value = "Search project by id", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the project", response = ProjectDetails.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<ProjectDetails> getProjectById(@PathVariable String projectId, @RequestParam String userId) {
        if (userDetailsService.findUserById(userId) == null) {
            throw new UserNotFoundException("User not found for the give id-" + userId);
        } else {
            ProjectDetails projectDetails = detailsService.getProjectById(projectId);
            if (projectDetails == null) {
                throw new ProjectNotFoundException("project not found for the given id-" + projectId);
            } else {
                return new ResponseEntity<>(projectDetails, HttpStatus.OK);
            }
        }
    }
}
