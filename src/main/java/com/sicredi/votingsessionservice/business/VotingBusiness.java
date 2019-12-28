package com.sicredi.votingsessionservice.business;

import com.sicredi.votingsessionservice.infrastructure.mongo.model.VotingEntity;
import com.sicredi.votingsessionservice.infrastructure.mongo.repository.VotingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class VotingBusiness {

    @Autowired
    private VotingRepository votingRepository;

    public Mono<VotingEntity> save(VotingEntity votingEntity){
        return votingRepository.save(votingEntity);
    }

    public Flux<VotingEntity> findAll(){
        return votingRepository.findAll();
    }

    public Flux<VotingEntity> resultAssembly(String assemblyCode){
        return votingRepository.countByAssemblyCodeAndVoting(assemblyCode, true);
    }
}
