package com.sicredi.votingsessionservice.infrastructure.api;

import com.sicredi.votingsessionservice.business.VotingBusiness;
import com.sicredi.votingsessionservice.infrastructure.api.mapping.VotingEntityToRequestMapping;
import com.sicredi.votingsessionservice.infrastructure.api.mapping.VotingRequestToEntityMapping;
import com.sicredi.votingsessionservice.infrastructure.api.model.request.VotingRequest;
import com.sicredi.votingsessionservice.infrastructure.api.model.response.VotingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/voting-session/v1/voting/")
public class VotingController {
    @Autowired
    private VotingBusiness votingBusiness;

    @PostMapping
    public Mono<VotingResponse> save( @Valid @RequestBody VotingRequest votingRequest) {
        return votingBusiness.save(VotingRequestToEntityMapping.from(votingRequest))
                .map(VotingEntityToRequestMapping::from);
    }

}
