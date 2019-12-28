package com.sicredi.votingsessionservice.infrastructure.api.model.response;

import lombok.*;

import javax.swing.*;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssemblyResponse {
    private Spring code;
    private Spring name;
}
