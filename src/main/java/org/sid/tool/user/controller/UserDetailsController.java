package org.sid.tool.user.controller;

import org.bson.types.ObjectId;
import org.sid.tool.models.UserDetail;
import org.sid.tool.user.services.UserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class UserDetailsController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final UserDetailsService userDetailsService;

    @Autowired
    public UserDetailsController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDetail> getUserById(@PathVariable("id") ObjectId id) {

        return userDetailsService.findUserById(id);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<UserDetail> getUserByName(@RequestParam String userName) {
        return userDetailsService.findUserDetailsByName(userName);
    }

    @RequestMapping(value = "/user/all", method = RequestMethod.GET)
    public ResponseEntity<List<UserDetail>> getAllUserDetails() {
        final ResponseEntity<List<UserDetail>> listResponseEntity = userDetailsService.fetchAllUserDetails();
        return listResponseEntity;
    }

    @RequestMapping(value = "/user/new", method = RequestMethod.POST)
    public ResponseEntity<Object> createNewUser(@RequestBody UserDetail userDetail) {
        return userDetailsService.createNewUser(userDetail);
    }
}
