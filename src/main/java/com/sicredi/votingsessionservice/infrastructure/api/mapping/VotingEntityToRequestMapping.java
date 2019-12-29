package com.sicredi.votingsessionservice.infrastructure.api.mapping;

import com.sicredi.votingsessionservice.infrastructure.api.model.response.VotingResponse;
import com.sicredi.votingsessionservice.infrastructure.mongo.model.VotingEntity;

import java.util.Date;


public class VotingEntityToRequestMapping {
    private VotingEntityToRequestMapping() {
    }

    public static VotingResponse from(VotingEntity voting) {
        return VotingResponse.builder()
                .id(voting.getId())
                .userCode(voting.getUserCode())
                .assemblyCode(voting.getAssemblyCode())
                .date(new Date())
                .build();
    }
}
