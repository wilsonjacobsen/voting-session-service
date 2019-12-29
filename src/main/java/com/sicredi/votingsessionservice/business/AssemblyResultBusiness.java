package com.sicredi.votingsessionservice.business;

import com.sicredi.votingsessionservice.infrastructure.kafka.Producer;
import com.sicredi.votingsessionservice.infrastructure.mongo.model.AssemblyEntity;
import com.sicredi.votingsessionservice.infrastructure.mongo.repository.AssemblyRepository;
import com.sicredi.votingsessionservice.infrastructure.mongo.repository.VotingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AssemblyResultBusiness  {

    private Long sleep;
    private String id;

    public void setSleep(Long sleep) {
        this.sleep = sleep;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Autowired
    private AssemblyRepository assemblyRepository;

    @Autowired
    private VotingRepository votingRepository;

    @Autowired
    private Producer producer;


    @Async
    public void run(String id, Long sleep) {
        try {
            Thread.sleep(sleep);

            result(id)
                    .map(assemblyEntity -> {
                        producer.sendMessage(assemblyEntity.toJson());
                        return assemblyEntity;
                    }).subscribe();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Mono<AssemblyEntity> result(String id) {
        return votingRepository.countByAssemblyCodeAndVoting(id, true)
                .switchIfEmpty(Mono.just(Long.valueOf(0)))
                .flatMap(yesVotes ->
                        votingRepository.countByAssemblyCodeAndVoting(id, false)
                                .switchIfEmpty(Mono.just(Long.valueOf(0)))
                                .flatMap(noVotes -> findAssembly(id, yesVotes, noVotes))
                ).flatMap(assemblyRepository::save);
    }

    private Mono<AssemblyEntity> findAssembly(String id, Long yesVotes, Long noVotes) {
        return assemblyRepository.findById(id)
                .map(assemblyEntity -> buildAssemblyResult(yesVotes, noVotes, assemblyEntity));
    }

    private AssemblyEntity buildAssemblyResult(Long yesVotes, Long noVotes, AssemblyEntity assemblyEntity) {
        return assemblyEntity.toBuilder()
                .yesVotes(yesVotes.intValue())
                .noVotes(noVotes.intValue())
                .build();
    }

}