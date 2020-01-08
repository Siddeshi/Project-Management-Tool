package org.sid.tool.user;

import org.sid.tool.models.UserDetails;
import org.sid.tool.service.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private UserDetailsService userDetailsService;

    @GetMapping
    @RequestMapping(value = "{id}")
    @ResponseBody
    public UserDetails getUserById(@RequestParam(value = "id") String id) {
        return userDetailsService.findUserDetailsById(id);
    }

    @PostMapping
    @RequestMapping(value = "/create")
    @ResponseBody
    public String createNewUser(@RequestBody UserDetails userDetails) {
        userDetailsService.createNewUser(userDetails);
        return "new user created";
    }
}
