package com.workout.domain.member.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Member {

    private Long id;

    private String email;

    private String username;

    private String password;

    private String profileImage;
}
