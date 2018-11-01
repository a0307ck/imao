package com.imao.auth.shiro;

import org.apache.shiro.authc.AuthenticationToken;


@SuppressWarnings("serial")
public class JWTToken implements AuthenticationToken {
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
