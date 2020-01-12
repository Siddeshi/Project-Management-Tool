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

/**
 * ProductBacklogController is a @RestController class, it exposes REST endpoints for handling product backlog
 *
 * @author siddesh
 * @since 09/Jan/2020
 */
@RestController
@RequestMapping(value = "/")
@Api(value = "tool", description = "Operations pertaining to ProductBacklog details")
public class ProductBacklogController {

    /**
     * Autowiring the dao class
     */
    private final ProductBacklogService productBacklogService;

    @Autowired
    public ProductBacklogController(ProductBacklogService productBacklogService) {
        this.productBacklogService = productBacklogService;
    }

    /**
     * Api is to create a new backlog
     *
     * @param backlog new backlog object
     * @return String status
     */
    @PostMapping(value = "/backlog/new")
    @ApiOperation(value = "Create new backlog", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created", response = String.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<String> createNewBacklog(@Valid @RequestBody ProductBacklog backlog) {
        productBacklogService.createNewBacklog(backlog);
        return new ResponseEntity<>("new product backlog added", HttpStatus.CREATED);
    }

    /**
     * Api is to delete the product by its id
     *
     * @param id id of the product backlog
     * @return String status
     */
    @DeleteMapping(value = "/backlog/delete/{id}")
    @ApiOperation(value = "Delete backlog", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted", response = String.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<String> deleteBacklog(@PathVariable String id) {
        ProductBacklog productBacklog = productBacklogService.findProductBacklogById(id);
        if (productBacklog == null) {
            throw new BacklogNotFoundException("No backlog for this id-" + id);
        } else {
            productBacklogService.deleteBacklog(id);
            return new ResponseEntity<>("prodcut backlog deleted", HttpStatus.OK);
        }
    }

    @GetMapping(value = "/backlog/{id}")
    @ApiOperation(value = "Get backlog", response = ProductBacklog.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get the product backlog", response = ProductBacklog.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<ProductBacklog> getBacklog(@PathVariable String backlogId) {
        if (productBacklogService.findProductBacklogById(backlogId) == null) {
            throw new BacklogNotFoundException("No backlog found for this id-" + backlogId);
        } else {
            return new ResponseEntity<>(productBacklogService.findProductBacklogById(backlogId), HttpStatus.OK);
        }
    }

    @PutMapping(value = "/backlog/update")
    @ApiOperation(value = "Update backlog", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Updated product backlog", response = String.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<String> updateBacklog(@Valid @RequestBody ProductBacklog productBacklog) {
        if (productBacklogService.findProductBacklogById(productBacklog.get_id()) == null) {
            throw new BacklogNotFoundException("No backlog found for this id-" + productBacklog.get_id());
        } else {
            productBacklogService.updateBacklog(productBacklog);
            return new ResponseEntity<>("Updated product backlog", HttpStatus.OK);
        }
    }
}