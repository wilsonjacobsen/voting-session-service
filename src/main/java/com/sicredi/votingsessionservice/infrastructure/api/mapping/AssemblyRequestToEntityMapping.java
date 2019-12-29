package com.sicredi.votingsessionservice.infrastructure.api.mapping;

import com.sicredi.votingsessionservice.infrastructure.api.model.request.AssemblyRequest;
import com.sicredi.votingsessionservice.infrastructure.mongo.model.AssemblyEntity;

import java.util.UUID;

public class AssemblyRequestToEntityMapping {
    private AssemblyRequestToEntityMapping(){ }
    public static AssemblyEntity from(AssemblyRequest assemblyRequest){
        return AssemblyEntity.builder()
                .id(UUID.randomUUID().toString())
                .name(assemblyRequest.getName())
                .build();
    }
}
