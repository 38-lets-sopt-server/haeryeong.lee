package org.sopt.dto.request;

public record SignUpRequest(
    String nickname,
    String email,
    String password
) {
}
