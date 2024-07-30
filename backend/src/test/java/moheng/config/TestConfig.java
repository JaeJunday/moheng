package moheng.config;

import moheng.auth.domain.OAuthClient;
import moheng.auth.domain.OAuthClientProvider;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {
    @Bean
    public OAuthClient oAuthClient() {
        return new KakaoStubOAuthClient();
    }
}
