package com.sicredi.votingsessionservice.business;

import com.sicredi.votingsessionservice.exception.NotFoundException;
import com.sicredi.votingsessionservice.exception.PreconditionFailed;
import com.sicredi.votingsessionservice.infrastructure.mongo.model.AssemblyEntity;
import com.sicredi.votingsessionservice.infrastructure.mongo.repository.AssemblyRepository;

import com.sicredi.votingsessionservice.infrastructure.mongo.repository.VotingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class AssemblyBusiness {

    @Autowired
    private AssemblyRepository assemblyRepository;

    @Autowired
    private VotingRepository votingRepository;

    @Autowired
    private AssemblyResultBusiness assemblyResultBusiness;

    public Mono<AssemblyEntity> save(AssemblyEntity assemblyEntity) {
        return assemblyRepository.save(assemblyEntity);
    }

    public Flux<AssemblyEntity> findAll() {
        return assemblyRepository.findAll();
    }

    public Mono<AssemblyEntity> findById(String id) {
        return assemblyRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Wave not found!")));
    }

    public Mono<AssemblyEntity> startAssembly(String id) {
        final Date startDate = new Date();
        final Long time=Long.valueOf(60000);
        return assemblyRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Wave not found!")))
                .filter(assemblyEntity -> assemblyEntity.getStartDate() == null)
                .switchIfEmpty(Mono.error(new PreconditionFailed("Assembly is active!")))
                .map(assemblyEntity -> assemblyEntity.toBuilder()
                        .startDate(startDate)
                        .finishDate(new Date(startDate.getTime() + time))
                        .build()
                )
                .map(assemblyEntity -> {
                    assemblyResultBusiness.run(id,time);
                    return assemblyEntity;
                })
                .flatMap(assemblyRepository::save);
    }

}
