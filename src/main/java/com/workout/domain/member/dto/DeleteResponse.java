package com.workout.domain.member.dto;

import com.workout.domain.member.model.Member;
import lombok.Getter;

@Getter
public class DeleteResponse {

    private DeletedMember deletedMember;

    public DeleteResponse(Member member) {
        this.deletedMember = new DeletedMember(member);
    }

    @Getter
    public static class DeletedMember {

        private Long id;

        private String email;

        private String username;

        public DeletedMember(Member member) {
            this.id = member.getId();
            this.email = member.getEmail();
            this.username = member.getUsername();
        }
    }
}