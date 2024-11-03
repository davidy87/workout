package com.workout.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateRequest {

    private Long memberId;

    private String newUsername;

    private String newPassword;

    private String newProfileImage;
}
