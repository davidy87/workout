package com.workout.domain.member.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberUpdateParam {

    private String email;

    private String username;

    private String password;

    private String profileImage;
}
