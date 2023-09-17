package money.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import money.domain.entity.User;

import java.util.UUID;


@Getter
@Builder(access = AccessLevel.PRIVATE)
public class UserDomain {
    private Integer index;
    private String id;
    private UUID uuid;
    private Long point;
    private String registerDatetime;
    private String updateDatetime;

    public static UserDomain init(User user) {
        return UserDomain.builder()
                .index(user.getIndex())
                .id(user.getId())
                .uuid(user.getUuid())
                .point(user.getPoint())
                .registerDatetime(
                        user.getRegisterDatetime() == null ? "0000-00-00 00:00:00" : user.getRegisterDatetime().toString()
                )
                .updateDatetime(
                        user.getUpdateDatetime() == null ? "0000-00-00 00:00:00" : user.getUpdateDatetime().toString()
                )
                .build();
    }
}
