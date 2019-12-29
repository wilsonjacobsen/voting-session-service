package com.sicredi.votingsessionservice.infrastructure.api.mapping;

import com.sicredi.votingsessionservice.infrastructure.api.model.response.AssemblyResponse;
import com.sicredi.votingsessionservice.infrastructure.mongo.model.AssemblyEntity;

public class AssemblyEntityToResponseMapping {
    private AssemblyEntityToResponseMapping() {
    }

    public static AssemblyResponse from(AssemblyEntity assembly) {
        return AssemblyResponse.builder()
                .id(assembly.getId())
                .name(assembly.getName())
                .startDate(assembly.getStartDate())
                .finishDate(assembly.getFinishDate())
                .yesVotes(assembly.getYesVotes())
                .noVotes(assembly.getNoVotes())
                .build();
    }
}
