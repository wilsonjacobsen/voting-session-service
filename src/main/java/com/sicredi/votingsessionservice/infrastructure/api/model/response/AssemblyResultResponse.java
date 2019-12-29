package com.sicredi.votingsessionservice.infrastructure.api.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.swing.*;
import java.util.Date;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AssemblyResultResponse {
    private String name;
    private Date startDate;
    private Date finishDate;
    private Integer yesVotes;
    private Integer noVotes;
}
