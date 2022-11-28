package org.example.business;


import org.example.domain.AccessToken;

public interface AccessTokenEncoder {
    String encode(AccessToken accessToken);
}
