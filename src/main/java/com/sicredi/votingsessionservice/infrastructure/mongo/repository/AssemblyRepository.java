package com.sicredi.votingsessionservice.infrastructure.mongo.repository;

import com.sicredi.votingsessionservice.infrastructure.mongo.model.AssemblyEntity;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Date;

@Repository
public interface AssemblyRepository extends ReactiveMongoRepository<AssemblyEntity,String> {


}
