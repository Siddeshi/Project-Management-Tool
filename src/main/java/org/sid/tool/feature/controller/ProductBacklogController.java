package org.sid.tool.feature.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.sid.tool.customexception.BacklogNotFoundException;
import org.sid.tool.feature.services.ProductBacklogService;
import org.sid.tool.models.ProductBacklog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/")
@Api(value = "tool", description = "Operations pertaining to ProductBacklog details")
public class ProductBacklogController {

    private final ProductBacklogService productBacklogService;

    @Autowired
    public ProductBacklogController(ProductBacklogService productBacklogService) {
        this.productBacklogService = productBacklogService;
    }

    @PostMapping(value = "/backlog/new")
    @ApiOperation(value = "Create new backlog",
            notes = "", response = ProductBacklog.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created", response = ProductBacklog.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<ProductBacklog> createNewBacklog(@Valid @RequestBody ProductBacklog backlog) {
        return new ResponseEntity<>(productBacklogService.createNewBacklog(backlog), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/backlog/delete/{id}")
    @ApiOperation(value = "Delete backlog",
            notes = "", response = ProductBacklog.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted", response = ProductBacklog.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<ProductBacklog> deleteBacklog(@PathVariable String id) {
        ProductBacklog productBacklog = productBacklogService.findProductBacklogById(id);
        if (productBacklog == null) {
            throw new BacklogNotFoundException("No backlog for this id-" + id);
        } else {
            productBacklogService.deleteBacklog(id);
            return new ResponseEntity<>(productBacklog, HttpStatus.OK);
        }
    }
}
