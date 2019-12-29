package com.sicredi.votingsessionservice.business;

import com.sicredi.votingsessionservice.exception.NotFoundException;
import com.sicredi.votingsessionservice.exception.PreconditionFailed;
import com.sicredi.votingsessionservice.infrastructure.mongo.model.AssemblyEntity;
import com.sicredi.votingsessionservice.infrastructure.mongo.model.VotingEntity;
import com.sicredi.votingsessionservice.infrastructure.mongo.repository.AssemblyRepository;
import com.sicredi.votingsessionservice.infrastructure.mongo.repository.VotingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class VotingBusiness {

    @Autowired
    private VotingRepository votingRepository;

    @Autowired
    private AssemblyRepository assemblyRepository;

    public Mono<VotingEntity> save(VotingEntity votingEntity){
        return assemblyRepository.findById(votingEntity.getId())
                .switchIfEmpty(Mono.error(new NotFoundException("Wave not found!")))
                .filter(this::isValidatedDate)
                .switchIfEmpty(Mono.error(new PreconditionFailed("Assembly is not active!")))
                .flatMap(voAssemblyEntity->votingRepository.save(votingEntity));
    }

    private boolean isValidatedDate(AssemblyEntity assemblyEntity) {
        final Date nowDate = new Date();
        return assemblyEntity.getStartDate().getTime() > nowDate.getTime() &&   assemblyEntity.getFinishDate().getTime() < nowDate.getTime();
    }

    public Flux<VotingEntity> findAll(){
        return votingRepository.findAll();
    }

    public Flux<VotingEntity> resultAssembly(String assemblyCode){
        return votingRepository.countByAssemblyCodeAndVoting(assemblyCode, true);
    }
}
