package com.sicredi.votingsessionservice.infrastructure.mongo.repository;

import com.sicredi.votingsessionservice.infrastructure.mongo.model.VotingEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface VotingRepository extends ReactiveMongoRepository<VotingEntity,String> {
    Flux<VotingEntity> countByAssemblyCodeAndVoting(String assemblyCode,Boolean voting);
}
