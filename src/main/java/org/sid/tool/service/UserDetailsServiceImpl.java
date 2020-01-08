package org.sid.tool.service;

import org.sid.tool.models.UserDetails;
import org.sid.tool.repos.UserDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDetailsRepo userDetailsRepo;

    @Override
    public UserDetails findUserDetailsById(String id) {
        return userDetailsRepo.findOne(id);
    }

    @Override
    public void createNewUser(UserDetails userDetails) {
        userDetailsRepo.save(userDetails);
    }
}
