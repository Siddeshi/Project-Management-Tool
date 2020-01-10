package org.sid.tool.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

@RestController
@RequestMapping(value = "/")
@Api(value = "tool", description = "Operations pertaining to user details")
public class UserDetailsController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final UserDetailsService userDetailsService;

    @Autowired
    public UserDetailsController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Get user details by id",
            notes = "", response = UserDetail.class)
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

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ApiOperation(value = "Get user details by name",
            notes = "", response = UserDetail.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the user", response = UserDetail.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<UserDetail> getUserByName(@RequestParam String userName) {
        UserDetail user = userDetailsService.findUserDetailsByName(userName);
        if (user == null) {
            throw new UserNotFoundException("user not found for the given name-" + userName);
        } else
            return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/all", method = RequestMethod.GET)
    @ApiOperation(value = "Get all users",
            notes = "", response = List.class)
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

    @RequestMapping(value = "/user/new", method = RequestMethod.POST)
    @ApiOperation(value = "Create new user",
            notes = "", response = UserDetail.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created the user", response = UserDetail.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<UserDetail> createNewUser(@RequestBody UserDetail userDetail) {
        return new ResponseEntity<>(userDetailsService.createNewUser(userDetail), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/user/delete/{id}")
    @ApiOperation(value = "Delete user",
            notes = "", response = UserDetail.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted the user", response = UserDetail.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<UserDetail> deleteUser(@PathVariable String id) {
        UserDetail user = userDetailsService.findUserById(id);
        if (user == null) {
            throw new UserNotFoundException("User not found for the given id-" + id);
        } else
            return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping(value = "/user/update/{id}")
    @ApiOperation(value = "Update user",
            notes = "", response = UserDetail.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated the user", response = UserDetail.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<UserDetail> updateUser(@PathVariable String id, @Valid @RequestBody UserDetail userDetail) {
        UserDetail user = userDetailsService.findUserById(id);
        if (user == null) {
            throw new UserNotFoundException("User not found for the given id-" + id);
        } else {
            userDetailsService.createNewUser(userDetail);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
