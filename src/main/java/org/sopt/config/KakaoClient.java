package org.sopt.config;

import org.sopt.dto.response.KakaoTokenResponse;
import org.sopt.dto.response.KakaoUserInfoResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class KakaoClient {

  private final RestClient restClient = RestClient.create();

  @Value("${oauth.kakao.client-id}")
  private String clientId;

  @Value("${oauth.kakao.client-secret}")
  private String clientSecret;

  @Value("${oauth.kakao.redirect-uri}")
  private String redirectUri;

  @Value("${oauth.kakao.token-uri}")
  private String tokenUri;

  @Value("${oauth.kakao.user-info-uri}")
  private String userInfoUri;

  public KakaoTokenResponse getToken(String code) {
    return restClient.post()
        .uri(tokenUri)
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .body("grant_type=authorization_code" + "&client_id=" + clientId + "&redirect_uri=" + redirectUri + "&code=" + code + "&client_secret=" + clientSecret)
        .retrieve()
        .body(KakaoTokenResponse.class);
  }

  public KakaoUserInfoResponse getUserInfo(String KakaoAccessToken) {
    return restClient.get()
        .uri(userInfoUri)
        .header(HttpHeaders.AUTHORIZATION, "Bearer " + KakaoAccessToken)
        .retrieve()
        .body(KakaoUserInfoResponse.class);
  }

}
