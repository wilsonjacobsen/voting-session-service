package com.sicredi.votingsessionservice.business;

import com.sicredi.votingsessionservice.business.model.UserInfo;
import com.sicredi.votingsessionservice.exception.NotFoundException;
import com.sicredi.votingsessionservice.exception.PreconditionFailed;
import com.sicredi.votingsessionservice.infrastructure.mongo.model.AssemblyEntity;
import com.sicredi.votingsessionservice.infrastructure.mongo.model.VotingEntity;
import com.sicredi.votingsessionservice.infrastructure.mongo.repository.AssemblyRepository;
import com.sicredi.votingsessionservice.infrastructure.mongo.repository.VotingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class VotingBusiness {

    @Autowired
    private VotingRepository votingRepository;

    @Autowired
    private AssemblyRepository assemblyRepository;

    public Mono<VotingEntity> save(VotingEntity votingEntity) {


        return votingRepository.countById(votingEntity.getUserCode() + "-" + votingEntity.getAssemblyCode())
                .filter(count -> count == 0)
                .switchIfEmpty(Mono.error(new PreconditionFailed("Already voted")))

                .filter(count -> isUserValided(votingEntity.getUserCode()))
                .switchIfEmpty(Mono.error(new PreconditionFailed("Invalid user")))

                .flatMap(count -> assemblyRepository.findById(votingEntity.getAssemblyCode()))
                .switchIfEmpty(Mono.error(new NotFoundException("Wave not found!")))

                .filter(this::isValidatedDate)
                .switchIfEmpty(Mono.error(new PreconditionFailed("Assembly is not active!")))

                .flatMap(voAssemblyEntity -> votingRepository.save(votingEntity));
    }

    private boolean isValidatedDate(AssemblyEntity assemblyEntity) {
        final Date nowDate = new Date();
        return assemblyEntity.getStartDate().getTime() < nowDate.getTime() && assemblyEntity.getFinishDate().getTime() > nowDate.getTime();
    }

    private Boolean isUserValided(String user) {
        try {
            final String uri = "https://user-info.herokuapp.com/users/" + user;

            RestTemplate restTemplate = new RestTemplate();
            UserInfo result = restTemplate.getForObject(uri, UserInfo.class);
            return (result != null && result.getStatus().equals("ABLE_TO_VOTE"));
        } catch (HttpClientErrorException ex) {
            return false;
        }
    }

}
