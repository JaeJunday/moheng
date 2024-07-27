package moheng.member.dto.response;

import moheng.member.domain.GenderType;
import moheng.member.domain.Member;

import java.time.LocalDate;

public class MemberResponse {
    private final Long id;
    private final String profileImageUrl;
    private final String nickname;
    private final LocalDate birthday;
    private final GenderType gender;

    public MemberResponse(final Member member) {
        this.id = member.getId();
        this.profileImageUrl = member.getProfileImageUrl();
        this.nickname = member.getNickName();
        this.birthday = member.getBirthday();
        this.gender = member.getGenderType();
    }

}
