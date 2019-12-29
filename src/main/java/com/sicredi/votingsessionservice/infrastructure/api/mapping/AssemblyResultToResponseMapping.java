package com.sicredi.votingsessionservice.infrastructure.api.mapping;

import com.sicredi.votingsessionservice.business.model.AssemblyResult;
import com.sicredi.votingsessionservice.infrastructure.api.model.response.AssemblyResultResponse;

public class AssemblyResultToResponseMapping {
    private AssemblyResultToResponseMapping(){ }
    public static AssemblyResultResponse from(AssemblyResult assemblyRequest){
        return AssemblyResultResponse.builder()
                .name(assemblyRequest.getName())
                .startDate(assemblyRequest.getStartDate())
                .finishDate(assemblyRequest.getFinishDate())
                .yesVotes(assemblyRequest.getYesVotes())
                .noVotes(assemblyRequest.getNoVotes())
                .build();
    }
}
