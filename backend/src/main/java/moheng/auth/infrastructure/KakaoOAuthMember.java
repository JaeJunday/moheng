package moheng.auth.infrastructure;

import com.fasterxml.jackson.annotation.JsonProperty;
import moheng.auth.domain.oauth.OAuthMember;
import moheng.global.annotation.Generated;

public class KakaoOAuthMember implements OAuthMember {
    @JsonProperty("id")
    private String socialLoginId;

    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    private static class KakaoAccount {

        @JsonProperty("email")
        private String email;
    }

    public KakaoOAuthMember(final String email, final String socialLoginId) {
        this.socialLoginId = socialLoginId;
        this.kakaoAccount = new KakaoAccount();
        this.kakaoAccount.email = email;
    }

    @Override
    public String getSocialLoginId() {
        return socialLoginId;
    }

    @Override
    public String getEmail() {
        return kakaoAccount.email;
    }
}

