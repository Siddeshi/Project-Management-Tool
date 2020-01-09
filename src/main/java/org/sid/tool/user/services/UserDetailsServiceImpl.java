package org.sid.tool.user.services;

import org.bson.types.ObjectId;
import org.sid.tool.models.UserDetail;
import org.sid.tool.repos.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private final UserDetailsRepository userDetailsRepo;

    public UserDetailsServiceImpl(UserDetailsRepository userDetailsRepo) {
        this.userDetailsRepo = userDetailsRepo;
    }

    @Override
    @Transactional
    public ResponseEntity<UserDetail> findUserById(ObjectId id) {

        return new ResponseEntity<>(userDetailsRepo.findBy_id(id), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<UserDetail> findUserDetailsByName(String userName) {
        return new ResponseEntity<>(userDetailsRepo.findByUserName(userName), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<List<UserDetail>> fetchAllUserDetails() {
        return new ResponseEntity<>(userDetailsRepo.findAll(), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<Object> createNewUser(UserDetail userDetails) {
        return new ResponseEntity<>(userDetailsRepo.save(userDetails), HttpStatus.CREATED);
    }
}
