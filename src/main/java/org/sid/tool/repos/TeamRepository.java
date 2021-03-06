package org.sid.tool.repos;

import org.sid.tool.models.Team;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends MongoRepository<Team, String> {

    boolean existsByTeamName(String teamName);
}
