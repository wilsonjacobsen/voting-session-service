package com.sicredi.votingsessionservice.infrastructure.api.mapping;

import com.sicredi.votingsessionservice.infrastructure.api.model.request.AssemblyRequest;
import com.sicredi.votingsessionservice.infrastructure.mongo.model.AssemblyEntity;

public class AssemblyMapping {
    private AssemblyMapping(){ }
    public static AssemblyEntity from(AssemblyRequest assemblyRequest){
        return AssemblyEntity.builder()
                .code(assemblyRequest.getCode())
                .name(assemblyRequest.getCode())
                .build();
    }
}
