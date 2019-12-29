package com.sicredi.votingsessionservice.infrastructure.api.model.request;

import lombok.*;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssemblyRequest {
    private String name;
}
