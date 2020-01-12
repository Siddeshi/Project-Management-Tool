package org.sid.tool.milestones.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.sid.tool.customexception.MilestoneNotFoundExcpetion;
import org.sid.tool.milestones.services.MilestonesServices;
import org.sid.tool.models.Milestone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/")
@Api(value = "tool", description = "Operations pertaining to milestone details")
public class MilestoneController {

    private final MilestonesServices milestonesServices;

    @Autowired
    public MilestoneController(MilestonesServices milestonesServices) {
        this.milestonesServices = milestonesServices;
    }

    @PostMapping(value = "/milestone/new")
    @ApiOperation(value = "Add a new milestone", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "New milestone added", response = String.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<String> addNewMilestone(@Valid @RequestBody Milestone milestone) {
        milestonesServices.createNewMilestone(milestone);
        return new ResponseEntity<>("New milestone added", HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/milestone/delete/{milestoneId}")
    @ApiOperation(value = "Delete milestone", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Milestone deleted", response = String.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<String> deleteMilestone(@PathVariable String milestoneId) {
        if (!milestonesServices.checkMilestoneExist(milestoneId)) {
            throw new MilestoneNotFoundExcpetion("Milestone is not exist-" + milestoneId);
        } else {
            milestonesServices.deleteMilestone(milestoneId);
            return new ResponseEntity<>("Milestone deleted", HttpStatus.OK);
        }
    }

    @PutMapping(value = "/milestone/update")
    @ApiOperation(value = "Update milestone", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Milestone updated", response = String.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<String> updateMilestione(@Valid @RequestBody Milestone milestone) {
        if (!milestonesServices.checkMilestoneExist(milestone.get_id())) {
            throw new MilestoneNotFoundExcpetion("milestone not exist-" + milestone.get_id());
        } else {
            milestonesServices.updateMilestone(milestone);
            return new ResponseEntity<>("Milestone updated", HttpStatus.OK);
        }
    }
}
