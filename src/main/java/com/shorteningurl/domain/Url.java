package com.shorteningurl.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class Url {
    private long urlId;
    private String originalUrl;
}
