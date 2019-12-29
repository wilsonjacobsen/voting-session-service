package com.sicredi.votingsessionservice.infrastructure.api;

import com.sicredi.votingsessionservice.business.AssemblyBusiness;
import com.sicredi.votingsessionservice.infrastructure.api.mapping.AssemblyMapping;
import com.sicredi.votingsessionservice.infrastructure.api.model.request.AssemblyRequest;
import com.sicredi.votingsessionservice.infrastructure.mongo.model.AssemblyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@CrossOrigin
@RestController
@RequestMapping("/voting-session/v1/voting/")
public class VotingController {
    @Autowired
    private AssemblyBusiness assemblyBusiness;

    @GetMapping
    public AssemblyRequest searchAll(){
        return null;
    }

    @PostMapping
    public Mono<AssemblyEntity> save(AssemblyRequest assembly){
            return assemblyBusiness.save(AssemblyMapping.from(assembly));
    }

}
