package org.california.resource;

import java.util.Date;

public class TokenExpired {
    private String token;
    private Date expires;

    public TokenExpired(String token, Date expires) {
        this.token = token;
        this.expires = expires;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpires() {
        return expires.toString();
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }
}
