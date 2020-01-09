package org.sid.tool.projects.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.sid.tool.customexception.ProjectNotFoundException;
import org.sid.tool.customexception.UserNotFoundException;
import org.sid.tool.likes.services.LikesServices;
import org.sid.tool.models.ProjectDetails;
import org.sid.tool.models.UserDetail;
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

@RestController
@RequestMapping(value = "/")
@Api(value = "tool", description = "Operations pertaining to project details")
public class ProjectDetailsController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final ProjectDetailsService detailsService;

    private final LikesServices likesServices;

    private final UserDetailsService userDetailsService;

    public ProjectDetailsController(ProjectDetailsService detailsService, LikesServices likesServices, UserDetailsService userDetailsService) {
        this.detailsService = detailsService;
        this.likesServices = likesServices;
        this.userDetailsService = userDetailsService;
    }

    @Autowired


    @GetMapping(value = "/projects/list", produces = "application/json")
    @ApiOperation(value = "List all projects",
            notes = "", response = List.class)
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

    @PostMapping(value = "/projects/new", produces = "application/json", consumes = "application/json")
    @ApiOperation(value = "Add new project",
            notes = "", response = ProjectDetails.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully added new project", response = ProjectDetails.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<ProjectDetails> createNewProject(@Valid @RequestBody ProjectDetails projectDetails) {
        return new ResponseEntity<>(detailsService.createNewProject(projectDetails), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/projects/delete/{id}", produces = "application/json")
    @ApiOperation(value = "Delete project",
            notes = "", response = ProjectDetails.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted the project", response = ProjectDetails.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<ProjectDetails> deleteProject(@PathVariable String id) {
        ProjectDetails oldProject = detailsService.getProjectById(id);
        if (oldProject == null) {
            throw new ProjectNotFoundException("project not found for the id-" + id);
        } else {
            detailsService.deleteProjectById(id);
            return new ResponseEntity<>(oldProject, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/projects", produces = "application/json")
    @ApiOperation(value = "Search projects",
            notes = "", response = List.class)
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

    @GetMapping(value = "/projects/{projectId}", produces = "application/json")
    @ApiOperation(value = "Search project by id",
            notes = "", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the project", response = ProjectDetails.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<ProjectDetails> getProjectById(@PathVariable String projectId, @RequestParam String userId) {
        UserDetail userDetail = userDetailsService.findUserById(userId);
        if (userDetail == null) {
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
