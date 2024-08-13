package moheng.keyword.infrastructure;

import moheng.auth.domain.oauth.KakaoOAuthMember;
import moheng.auth.domain.oauth.OAuthAccessToken;
import moheng.auth.exception.InvalidOAuthServiceException;
import moheng.keyword.dto.TripContentIdsByKeywordResponse;
import moheng.keyword.exception.InvalidAIServerException;
import moheng.keyword.exception.TripRecommendByKeywordRequest;
import moheng.keyword.service.KeywordFilterModelClient;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Component
public class AiKeywordModelClient implements KeywordFilterModelClient {
    private static final String RECOMMEND_TRIP_LIST_BY_KEYWORD_REQUEST_URL = "http://localhost:8000";
    private final RestTemplate restTemplate;

    public AiKeywordModelClient(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public TripContentIdsByKeywordResponse findRecommendTripContentIdsByKeywords(final TripRecommendByKeywordRequest request) {
        return requestRecommendTrips(request);
    }

    private TripContentIdsByKeywordResponse requestRecommendTrips(TripRecommendByKeywordRequest request) {
        final ResponseEntity<TripContentIdsByKeywordResponse> contentIds = restTemplate.postForEntity(RECOMMEND_TRIP_LIST_BY_KEYWORD_REQUEST_URL, request, TripContentIdsByKeywordResponse.class);

        if(contentIds.getStatusCode().is2xxSuccessful()) {
            return contentIds.getBody();
        }
        throw new InvalidAIServerException("AI 서버에 예기치 못한 오류가 발생했습니다.");
    }
}
