package com.sicredi.votingsessionservice.infrastructure.api;

import com.sicredi.votingsessionservice.business.AssemblyBusiness;
import com.sicredi.votingsessionservice.infrastructure.api.mapping.AssemblyEntityToResponseMapping;
import com.sicredi.votingsessionservice.infrastructure.api.mapping.AssemblyRequestToEntityMapping;
import com.sicredi.votingsessionservice.infrastructure.api.model.request.AssemblyRequest;
import com.sicredi.votingsessionservice.infrastructure.api.model.response.AssemblyResponse;
import com.sicredi.votingsessionservice.infrastructure.mongo.model.AssemblyEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/voting-session/v1/assemblies/")
public class AssemblyController {
    @Autowired
    private AssemblyBusiness assemblyBusiness;

            private static final Logger logger = LoggerFactory.getLogger(AssemblyController.class);

    @GetMapping
    public AssemblyRequest searchAll() {
        return null;
    }


    @GetMapping("/sessions/{id}")
    public Mono<AssemblyResponse> findSession(@PathVariable String id) {
        return assemblyBusiness.findById(id)
                .map(AssemblyEntityToResponseMapping::from);
    }

    @PostMapping()
    public Mono<AssemblyEntity> save(@Valid @RequestBody AssemblyRequest assembly) {
        logger.info(String.format(" -> Creating Assembly"));

        return assemblyBusiness.save(AssemblyRequestToEntityMapping.from(assembly));
    }

    @PatchMapping("/sessions/{id}/start")
    public Mono<AssemblyResponse> startSession(@PathVariable String id) {
        logger.info(String.format(" -> Start Assembly"));

        return assemblyBusiness.startAssembly(id)
                .map(AssemblyEntityToResponseMapping::from);
    }


}
