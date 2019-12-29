package com.sicredi.votingsessionservice.business;

import com.sicredi.votingsessionservice.infrastructure.mongo.model.AssemblyEntity;
import com.sicredi.votingsessionservice.infrastructure.mongo.repository.AssemblyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class AssemblyBusiness {

    @Autowired
    private AssemblyRepository assemblyRepository;

    public Mono<AssemblyEntity> save(AssemblyEntity assemblyEntity) {
        return assemblyRepository.save(assemblyEntity);
    }

    public Flux<AssemblyEntity> findAll(){
        return assemblyRepository.findAll();
    }

    public Mono<AssemblyEntity> findById(String id){
        return assemblyRepository.findById(id);
    }

    public Mono<AssemblyEntity> startAssembly(String id){
        final Date startDate = new Date();
        return assemblyRepository.findById(id)
                .map(assemblyEntity -> assemblyEntity.toBuilder()
                        .startDate(startDate)
                        .finishDate(new Date(startDate.getTime() + 60000))
                        .build()
                ).flatMap(assemblyRepository::save);
    }

}
