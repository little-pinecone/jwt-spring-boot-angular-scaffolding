package in.keepgrowing.jwtspringbootangularscaffolding.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@ConfigurationProperties(prefix = "security.jwt")
public class TokenProperties {

    private String loginPath = "/api/login";

    private String header = "Authorization";

    private String prefix = "Bearer ";

    private int expiration = 86400;

    private String secret = "JwtSecretKey";

    public String getLoginPath() {
        return loginPath;
    }

    public String getHeader() {
        return header;
    }

    public String getPrefix() {
        return prefix;
    }

    public int getExpiration() {
        return expiration;
    }

    public String getSecret() {
        return secret;
    }
}
