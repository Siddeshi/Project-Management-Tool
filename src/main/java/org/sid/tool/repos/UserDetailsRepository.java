package org.sid.tool.repos;

import org.sid.tool.models.UserDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDetailsRepository extends MongoRepository<UserDetail, String> {

    @Override
    List<UserDetail> findAll();

    boolean existsByUserName(String userName);

    UserDetail findByUserName(String userName);

    UserDetail findBy_id(String id);
}