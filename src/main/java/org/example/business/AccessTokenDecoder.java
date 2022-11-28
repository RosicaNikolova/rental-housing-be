package org.example.business;

import org.example.domain.AccessToken;

public interface AccessTokenDecoder {
    AccessToken decode(String accessTokenEncoded);
}
