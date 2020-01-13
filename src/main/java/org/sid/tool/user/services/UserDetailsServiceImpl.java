package org.sid.tool.user.services;

import org.sid.tool.models.UserDetail;
import org.sid.tool.repos.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Implementation of dao interface for maintaining user transactions
 *
 * @author siddesh
 * @since 09/Jan/2020
 */
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {


    /**
     * Autowiring the user repo
     */
    private UserDetailsRepository userDetailsRepo;

    @Autowired
    public UserDetailsServiceImpl(UserDetailsRepository userDetailsRepo) {
        this.userDetailsRepo = userDetailsRepo;
    }

    public UserDetailsServiceImpl() {
    }

    /**
     * Fetch user details by user id
     *
     * @param id id of the user
     * @return UserDetails details of user
     */
    @Override
    public UserDetail findUserById(String id) {
        return userDetailsRepo.findBy_id(id);
    }

    /**
     * Fetch all the users
     *
     * @return List<UserDetail> list of users details
     */
    @Override
    public List<UserDetail> fetchAllUserDetails() {
        return userDetailsRepo.findAll();
    }

    /**
     * Create a new user
     * @param userDetails user details
     * @return UserDetail
     */
    @Override
    public UserDetail createNewUser(UserDetail userDetails) {
        return userDetailsRepo.save(userDetails);
    }

    /**
     * Delete user by user id
     * @param id id of the user
     */
    @Override
    public void deleteUser(String id) {
        userDetailsRepo.delete(id);
    }

    /**
     * Check user existence by his/her name
     *
     * @param name
     * @return boolean true/false
     */
    @Override
    public boolean checkUserExists(String name) {
        return userDetailsRepo.existsByUserName(name);
    }

    /**
     * Check user is exists by his/her id
     *
     * @param id id of the user
     * @return boolean true/false
     */
    @Override
    public boolean checkUserExistsById(String id) {
        return userDetailsRepo.exists(id);
    }

    /**
     * Update user details
     *
     * @param userDetail user details
     * @return UserDetail
     */
    @Override
    public UserDetail updateUserDetails(UserDetail userDetail) {
        return userDetailsRepo.save(userDetail);
    }
}
