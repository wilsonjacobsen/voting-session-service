package com.sicredi.votingsessionservice.infrastructure.api.mapping;

import com.sicredi.votingsessionservice.infrastructure.api.model.request.VotingRequest;
import com.sicredi.votingsessionservice.infrastructure.mongo.model.VotingEntity;
import java.util.Date;
import java.util.UUID;


public class VotingRequestToEntityMapping {
    private VotingRequestToEntityMapping(){ }
    public static VotingEntity from(VotingRequest votingRequest){
        return VotingEntity.builder()
                .id(votingRequest.getUserCode()+"-"+votingRequest.getAssemblyCode())
                .userCode(votingRequest.getUserCode())
                .assemblyCode(votingRequest.getAssemblyCode())
                .voting(votingRequest.getVoting())
                .date(new Date())
                .build();
    }
}
