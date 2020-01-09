package org.sid.tool.user.services;

import org.sid.tool.models.UserDetail;
import org.sid.tool.repos.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {


    private final UserDetailsRepository userDetailsRepo;

    @Autowired
    public UserDetailsServiceImpl(UserDetailsRepository userDetailsRepo) {
        this.userDetailsRepo = userDetailsRepo;
    }

    @Override
    public UserDetail findUserById(String id) {
        return userDetailsRepo.findBy_id(id);
    }

    @Override
    public UserDetail findUserDetailsByName(String userName) {
        return userDetailsRepo.findByUserName(userName);
    }

    @Override
    public List<UserDetail> fetchAllUserDetails() {
        return userDetailsRepo.findAll();
    }

    @Override
    public UserDetail createNewUser(UserDetail userDetails) {
        return userDetailsRepo.save(userDetails);
    }

    @Override
    public void deleteUser(String id) {
        userDetailsRepo.delete(id);
    }
}
