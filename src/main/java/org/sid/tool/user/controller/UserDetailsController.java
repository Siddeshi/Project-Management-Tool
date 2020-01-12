package org.sid.tool.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.sid.tool.customexception.UserModificationException;
import org.sid.tool.customexception.UserNotFoundException;
import org.sid.tool.models.UserDetail;
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
 * UserDetailsController is @RestController class that exposes REST endpoints for controlling the users
 *
 * @author siddesh
 * @since 06/Jan/2020
 */
@RestController
@RequestMapping(value = "/")
@Api(value = "tool", description = "Operations pertaining to user details")
public class UserDetailsController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * Autowiring the dao layer
     */
    private final UserDetailsService userDetailsService;

    @Autowired
    public UserDetailsController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Api is to get the complete user details by user id
     *
     * @param id id of the user
     * @return UserDetail
     * @throws UserNotFoundException throws if user is not exist
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Get user details by id", response = UserDetail.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the user", response = UserDetail.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<UserDetail> getUserById(@PathVariable("id") String id) {

        UserDetail user = userDetailsService.findUserById(id);
        if (user == null) {
            throw new UserNotFoundException("user not found for the given id-" + id);
        } else
            return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Api is to get list of all the users
     *
     * @return List<UserDetail> list of users
     */
    @RequestMapping(value = "/user/all", method = RequestMethod.GET)
    @ApiOperation(value = "Get all users", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved all users", response = UserDetail.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<List<UserDetail>> getAllUserDetails() {

        List<UserDetail> listOfUsers = userDetailsService.fetchAllUserDetails();
        if (listOfUsers == null || listOfUsers.size() == 0)
            throw new UserNotFoundException("users are not available");
        else
            return new ResponseEntity<>(listOfUsers, HttpStatus.OK);
    }

    /**
     * Api is to create a new user
     *
     * @param userDetail accepts valid user details
     * @return String status
     */
    @RequestMapping(value = "/user/new", method = RequestMethod.POST)
    @ApiOperation(value = "Create new user", response = UserDetail.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created the user", response = UserDetail.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<String> createNewUser(@Valid @RequestBody UserDetail userDetail, @RequestParam String username) {
        if (userDetailsService.checkUserExists(username)) {
            throw new UserModificationException("User with this name is already exists");
        } else {
            userDetailsService.createNewUser(userDetail);
            return new ResponseEntity<>("created new user", HttpStatus.CREATED);
        }
    }

    /**
     * Api is to delete the user by id
     * @param id id of the user
     * @return String status
     * @exception UserNotFoundException if the user id not exist
     */
    @DeleteMapping(value = "/user/delete/{id}")
    @ApiOperation(value = "Delete user", response = UserDetail.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted the user", response = UserDetail.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        if (!userDetailsService.checkUserExistsById(id)) {
            throw new UserNotFoundException("User not found for the given id-" + id);
        } else {
            userDetailsService.deleteUser(id);
            return new ResponseEntity<>("deleted the user", HttpStatus.OK);
        }
    }

    /**
     * Api is to update the user by user id
     * @param id id of the user id
     * @param userDetail accepts valid user details
     * @return String status
     * @exception UserNotFoundException throws if the user id not exist
     */
    @PutMapping(value = "/user/update/{id}")
    @ApiOperation(value = "Update user", response = UserDetail.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated the user", response = UserDetail.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<String> updateUser(@PathVariable String id, @Valid @RequestBody UserDetail userDetail) {
        if (!userDetailsService.checkUserExistsById(id)) {
            throw new UserNotFoundException("User not found for the given id-" + id);
        } else {
            userDetailsService.updateUserDetails(userDetail);
        }
        return new ResponseEntity<>("updated user details", HttpStatus.OK);
    }
}
